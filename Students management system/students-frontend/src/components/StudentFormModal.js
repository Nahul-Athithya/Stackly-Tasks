import React, { useState, useEffect } from 'react';
import { X } from 'lucide-react';

const StudentFormModal = ({ isOpen, onClose, onSave, studentToEdit }) => {
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        age: '',
        department: ''
    });

    useEffect(() => {
        if (studentToEdit) {
            setFormData(studentToEdit);
        } else {
            setFormData({ name: '', email: '', age: '', department: '' });
        }
    }, [studentToEdit, isOpen]);

    if (!isOpen) return null;

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSave(formData);
    };

    return (
        <div className="fixed inset-0 bg-black/60 backdrop-blur-sm flex justify-center items-center z-50">
            <div className="bg-[#1e1b4b] border border-indigo-900/50 rounded-xl p-6 w-full max-w-md shadow-2xl">
                <div className="flex justify-between items-center mb-6">
                    <h2 className="text-xl font-semibold text-indigo-100">
                        {studentToEdit ? 'Edit Student' : 'Add New Student'}
                    </h2>
                    <button onClick={onClose} className="text-indigo-300 hover:text-white transition-colors">
                        <X size={24} />
                    </button>
                </div>

                <form onSubmit={handleSubmit} className="space-y-4">
                    <div>
                        <label className="block text-sm font-medium text-indigo-200 mb-1">Name</label>
                        <input
                            type="text"
                            name="name"
                            required
                            value={formData.name}
                            onChange={handleChange}
                            className="w-full bg-[#131127] border border-indigo-900/50 rounded-lg px-4 py-2.5 text-indigo-100 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition-all"
                            placeholder="e.g. Arjun Kumar"
                        />
                    </div>
                    
                    <div>
                        <label className="block text-sm font-medium text-indigo-200 mb-1">Email</label>
                        <input
                            type="email"
                            name="email"
                            required
                            value={formData.email}
                            onChange={handleChange}
                            className="w-full bg-[#131127] border border-indigo-900/50 rounded-lg px-4 py-2.5 text-indigo-100 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition-all"
                            placeholder="e.g. arjun@college.edu"
                        />
                    </div>

                    <div className="grid grid-cols-2 gap-4">
                        <div>
                            <label className="block text-sm font-medium text-indigo-200 mb-1">Age</label>
                            <input
                                type="number"
                                name="age"
                                required
                                min="18"
                                max="60"
                                value={formData.age}
                                onChange={handleChange}
                                className="w-full bg-[#131127] border border-indigo-900/50 rounded-lg px-4 py-2.5 text-indigo-100 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition-all"
                                placeholder="e.g. 20"
                            />
                        </div>
                        
                        <div>
                            <label className="block text-sm font-medium text-indigo-200 mb-1">Department</label>
                            <select
                                name="department"
                                required
                                value={formData.department}
                                onChange={handleChange}
                                className="w-full bg-[#131127] border border-indigo-900/50 rounded-lg px-4 py-2.5 text-indigo-100 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition-all"
                            >
                                <option value="" disabled>Select Dept</option>
                                <option value="CSE">CSE</option>
                                <option value="ECE">ECE</option>
                                <option value="MECH">MECH</option>
                                <option value="CIVIL">CIVIL</option>
                                <option value="IT">IT</option>
                            </select>
                        </div>
                    </div>

                    <div className="mt-8 flex justify-end gap-3 pt-4 border-t border-indigo-900/30">
                        <button
                            type="button"
                            onClick={onClose}
                            className="px-5 py-2.5 rounded-lg text-indigo-300 hover:bg-indigo-900/30 transition-colors font-medium"
                        >
                            Cancel
                        </button>
                        <button
                            type="submit"
                            className="px-5 py-2.5 bg-indigo-500 hover:bg-indigo-400 text-white rounded-lg transition-colors shadow-lg shadow-indigo-500/20 font-medium"
                        >
                            {studentToEdit ? 'Save Changes' : 'Add Student'}
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default StudentFormModal;
