/**
 * EmployeeModal — Add / Edit employee form
 * Reusable: mode is determined by whether `employee` prop is null (add) or an object (edit)
 */

import { useState, useEffect } from 'react';
import Modal from './ui/Modal.jsx';
import InputField from './ui/InputField.jsx';
import Button from './ui/Button.jsx';

const EMPTY_FORM = {
  firstName: '',
  lastName: '',
  email: '',
  salary: '',
  department: '',
};

const EmployeeModal = ({ isOpen, onClose, onSubmit, employee }) => {
  const isEditMode = !!employee;
  const [form, setForm] = useState(EMPTY_FORM);
  const [errors, setErrors] = useState({});
  const [submitting, setSubmitting] = useState(false);

  // Pre-fill form when editing
  useEffect(() => {
    if (employee) {
      setForm({
        firstName: employee.firstName || '',
        lastName: employee.lastName || '',
        email: employee.email || '',
        salary: employee.salary?.toString() || '',
        department: employee.department || '',
      });
    } else {
      setForm(EMPTY_FORM);
    }
    setErrors({});
  }, [employee, isOpen]);

  const handleChange = (field) => (e) => {
    setForm((prev) => ({ ...prev, [field]: e.target.value }));
    setErrors((prev) => ({ ...prev, [field]: '' }));
  };

  const validate = () => {
    const newErrors = {};
    if (!form.firstName.trim()) newErrors.firstName = 'First name is required';
    if (!form.lastName.trim()) newErrors.lastName = 'Last name is required';
    if (!form.email.trim()) newErrors.email = 'Email is required';
    else if (!/^\S+@\S+\.\S+$/.test(form.email)) newErrors.email = 'Invalid email format';
    if (!form.salary) newErrors.salary = 'Salary is required';
    else if (isNaN(form.salary) || Number(form.salary) <= 0)
      newErrors.salary = 'Salary must be a positive number';
    if (!form.department.trim()) newErrors.department = 'Department is required';
    return newErrors;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const validationErrors = validate();
    if (Object.keys(validationErrors).length > 0) {
      setErrors(validationErrors);
      return;
    }
    setSubmitting(true);
    try {
      await onSubmit({
        ...form,
        salary: parseFloat(form.salary),
      });
      onClose();
    } catch (err) {
      console.error('Submit error:', err);
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <Modal
      isOpen={isOpen}
      onClose={onClose}
      title={isEditMode ? 'Edit Employee' : 'Add New Employee'}
    >
      <form onSubmit={handleSubmit} className="flex flex-col gap-4">
        {/* Name Row */}
        <div className="grid grid-cols-2 gap-3">
          <InputField
            id="firstName"
            label="First Name"
            value={form.firstName}
            onChange={handleChange('firstName')}
            placeholder="Arjun"
            required
            error={errors.firstName}
          />
          <InputField
            id="lastName"
            label="Last Name"
            value={form.lastName}
            onChange={handleChange('lastName')}
            placeholder="Sharma"
            required
            error={errors.lastName}
          />
        </div>

        {/* Email */}
        <InputField
          id="email"
          label="Email"
          type="email"
          value={form.email}
          onChange={handleChange('email')}
          placeholder="arjun.sharma@corp.com"
          required
          error={errors.email}
        />

        {/* Salary */}
        <InputField
          id="salary"
          label="Salary (₹)"
          type="number"
          value={form.salary}
          onChange={handleChange('salary')}
          placeholder="72000"
          required
          error={errors.salary}
        />

        {/* Department */}
        <InputField
          id="department"
          label="Department"
          value={form.department}
          onChange={handleChange('department')}
          placeholder="Engineering"
          required
          error={errors.department}
        />

        {/* Action Buttons */}
        <div className="flex justify-end gap-3 pt-2 border-t border-gray-100 mt-1">
          <Button variant="outline" size="md" onClick={onClose} type="button">
            Cancel
          </Button>
          <Button
            variant="primary"
            size="md"
            type="submit"
            disabled={submitting}
          >
            {submitting ? 'Saving...' : isEditMode ? 'Update Employee' : 'Add Employee'}
          </Button>
        </div>
      </form>
    </Modal>
  );
};

export default EmployeeModal;
