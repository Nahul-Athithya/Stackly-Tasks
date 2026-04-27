import React, { useState, useEffect } from 'react';
import { Plus, Search, ChevronDown } from 'lucide-react';
import { getStudents, createStudent, updateStudent, deleteStudent } from './api/studentService';
import StudentFormModal from './components/StudentFormModal';

function App() {
  const [students, setStudents] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [departmentFilter, setDepartmentFilter] = useState('');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [studentToEdit, setStudentToEdit] = useState(null);

  // Fetch students on mount
  useEffect(() => {
    fetchStudents();
  }, []);

  const fetchStudents = async () => {
    try {
      const data = await getStudents();
      setStudents(data);
    } catch (error) {
      console.error("Error fetching students:", error);
    }
  };

  const handleSaveStudent = async (studentData) => {
    try {
      if (studentToEdit) {
        await updateStudent(studentToEdit.id, studentData);
      } else {
        await createStudent(studentData);
      }
      setIsModalOpen(false);
      setStudentToEdit(null);
      fetchStudents(); // Refresh the list
    } catch (error) {
      console.error("Error saving student:", error);
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm("Are you sure you want to delete this student?")) {
      try {
        await deleteStudent(id);
        fetchStudents();
      } catch (error) {
        console.error("Error deleting student:", error);
      }
    }
  };

  const openAddModal = () => {
    setStudentToEdit(null);
    setIsModalOpen(true);
  };

  const openEditModal = (student) => {
    setStudentToEdit(student);
    setIsModalOpen(true);
  };

  // Filtering
  const filteredStudents = students.filter(s => {
    const matchesSearch = 
      s.name.toLowerCase().includes(searchTerm.toLowerCase()) || 
      s.email.toLowerCase().includes(searchTerm.toLowerCase()) ||
      s.department.toLowerCase().includes(searchTerm.toLowerCase());
    
    const matchesDept = departmentFilter ? s.department === departmentFilter : true;
    
    return matchesSearch && matchesDept;
  });

  return (
    <div className="min-h-screen bg-[#11101d] text-slate-300 font-sans p-8">
      <div className="max-w-6xl mx-auto bg-[#1a1b2e] rounded-2xl shadow-xl overflow-hidden border border-indigo-900/30">
        
        {/* Header Section */}
        <div className="p-8 pb-6">
          <h1 className="text-3xl font-bold text-indigo-300 mb-2">Student Management System</h1>
          <p className="text-slate-400">View, search, and manage all student records</p>
        </div>

        {/* Toolbar Section */}
        <div className="px-8 pb-6 flex flex-col md:flex-row justify-between items-center gap-4 border-b border-indigo-900/30">
          <div className="flex w-full md:w-auto gap-4">
            {/* Search */}
            <div className="relative w-full md:w-80">
              <input 
                type="text" 
                placeholder="Search by name, email or department..." 
                className="w-full bg-[#11101d] border border-indigo-900/50 rounded-lg pl-4 pr-10 py-2.5 text-slate-300 focus:outline-none focus:border-indigo-500 transition-colors"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
              />
            </div>
            
            {/* Department Filter */}
            <div className="relative min-w-[160px]">
              <select 
                className="w-full bg-[#11101d] border border-indigo-900/50 rounded-lg px-4 py-2.5 text-slate-300 appearance-none focus:outline-none focus:border-indigo-500 cursor-pointer"
                value={departmentFilter}
                onChange={(e) => setDepartmentFilter(e.target.value)}
              >
                <option value="">All departments</option>
                <option value="CSE">CSE</option>
                <option value="ECE">ECE</option>
                <option value="MECH">MECH</option>
                <option value="CIVIL">CIVIL</option>
                <option value="IT">IT</option>
              </select>
              <ChevronDown className="absolute right-3 top-3 text-slate-400 pointer-events-none" size={18} />
            </div>
          </div>

          <button 
            onClick={openAddModal}
            className="w-full md:w-auto flex items-center justify-center gap-2 bg-[#d8cbf5] hover:bg-[#c9b7eb] text-[#11101d] px-5 py-2.5 rounded-lg font-semibold transition-colors"
          >
            <Plus size={20} />
            Add student
          </button>
        </div>

        {/* Table Section */}
        <div className="overflow-x-auto">
          <table className="w-full text-left border-collapse">
            <thead>
              <tr className="text-xs uppercase tracking-wider text-slate-500 border-b border-indigo-900/30">
                <th className="px-8 py-4 font-medium">ID</th>
                <th className="px-8 py-4 font-medium">NAME</th>
                <th className="px-8 py-4 font-medium">EMAIL</th>
                <th className="px-8 py-4 font-medium">AGE</th>
                <th className="px-8 py-4 font-medium">DEPT</th>
                <th className="px-8 py-4 font-medium">ACTIONS</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-indigo-900/10">
              {filteredStudents.length > 0 ? (
                filteredStudents.map((student) => (
                  <tr key={student.id} className="hover:bg-[#202138] transition-colors group">
                    <td className="px-8 py-4 text-slate-500">{student.id}</td>
                    <td className="px-8 py-4 font-medium text-slate-200">{student.name}</td>
                    <td className="px-8 py-4 text-indigo-300">{student.email}</td>
                    <td className="px-8 py-4 text-slate-300">{student.age}</td>
                    <td className="px-8 py-4">
                      <span className="px-3 py-1 text-xs font-medium bg-indigo-900/30 text-indigo-300 border border-indigo-500/20 rounded-md">
                        {student.department}
                      </span>
                    </td>
                    <td className="px-8 py-4">
                      <div className="flex gap-2 opacity-100 md:opacity-0 md:group-hover:opacity-100 transition-opacity">
                        <button 
                          onClick={() => openEditModal(student)}
                          className="px-4 py-1.5 text-sm font-medium text-slate-300 border border-indigo-900/50 rounded-md hover:bg-indigo-900/30 hover:border-indigo-500/50 transition-colors"
                        >
                          Edit
                        </button>
                        <button 
                          onClick={() => handleDelete(student.id)}
                          className="px-4 py-1.5 text-sm font-medium text-red-400 border border-red-900/30 rounded-md hover:bg-red-900/20 hover:border-red-500/50 transition-colors"
                        >
                          Delete
                        </button>
                      </div>
                    </td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="6" className="px-8 py-12 text-center text-slate-500">
                    No students found.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>

        {/* Footer / Pagination */}
        <div className="px-8 py-4 border-t border-indigo-900/30 flex justify-between items-center text-sm text-slate-500 bg-[#161726]">
          <div>
            Showing {filteredStudents.length} of {students.length}
          </div>
          <div>
            Page 1 of 1
          </div>
        </div>
      </div>

      <StudentFormModal 
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        onSave={handleSaveStudent}
        studentToEdit={studentToEdit}
      />
    </div>
  );
}

export default App;
