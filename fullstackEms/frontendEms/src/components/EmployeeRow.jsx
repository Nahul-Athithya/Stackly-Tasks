/**
 * EmployeeRow — A single table row for one employee
 * Reusable: receives employee data and callback handlers via props
 */

import Button from './ui/Button.jsx';

const formatSalary = (amount) =>
  new Intl.NumberFormat('en-IN', {
    style: 'currency',
    currency: 'INR',
    maximumFractionDigits: 0,
  }).format(amount);

const EmployeeRow = ({ employee, onEdit, onDelete }) => {
  const { id, firstName, lastName, email, salary, department } = employee;

  return (
    <tr className="border-b border-gray-100 hover:bg-gray-50 transition-colors duration-100">
      {/* ID */}
      <td className="px-5 py-3.5 text-sm text-gray-500 w-12">{id}</td>

      {/* Name */}
      <td className="px-5 py-3.5 text-sm font-medium text-gray-800">
        {firstName} {lastName}
      </td>

      {/* Email */}
      <td className="px-5 py-3.5 text-sm">
        <a
          href={`mailto:${email}`}
          className="text-blue-600 hover:text-blue-800 hover:underline transition-colors"
        >
          {email}
        </a>
      </td>

      {/* Salary */}
      <td className="px-5 py-3.5 text-sm text-gray-700 font-medium">
        {formatSalary(salary)}
      </td>

      {/* Department */}
      <td className="px-5 py-3.5 text-sm text-gray-600">{department}</td>

      {/* Actions */}
      <td className="px-5 py-3.5">
        <div className="flex gap-2 items-center">
          <Button variant="outline" size="sm" onClick={() => onEdit(employee)}>
            Edit
          </Button>
          <Button variant="danger" size="sm" onClick={() => onDelete(id)}>
            Delete
          </Button>
        </div>
      </td>
    </tr>
  );
};

export default EmployeeRow;
