import { useState, useEffect, useCallback } from 'react';
import Header from './components/Header.jsx';
import SearchBar from './components/SearchBar.jsx';
import EmployeeTable from './components/EmployeeTable.jsx';
import EmployeeModal from './components/EmployeeModal.jsx';
import Pagination from './components/Pagination.jsx';
import {
  getEmployeesPaginated,
  searchByDepartment,
  createEmployee,
  updateEmployee,
  deleteEmployee,
} from './api/employeeService.js';

const PAGE_SIZE = 10;

export default function App() {
  // ─── State ────────────────────────────────────────────────────────────────
  const [employees, setEmployees]       = useState([]);
  const [allEmployees, setAllEmployees] = useState([]);   // for client-side search
  const [totalElements, setTotalElements] = useState(0);
  const [totalPages, setTotalPages]     = useState(0);
  const [currentPage, setCurrentPage]   = useState(0);
  const [loading, setLoading]           = useState(false);

  const [searchQuery, setSearchQuery]   = useState('');
  const [selectedDept, setSelectedDept] = useState('');
  const [departments, setDepartments]   = useState([]);

  const [modalOpen, setModalOpen]       = useState(false);
  const [editEmployee, setEditEmployee] = useState(null);

  // ─── Fetch paginated (default view) ───────────────────────────────────────
  const fetchPage = useCallback(async (page = 0) => {
    setLoading(true);
    try {
      const res = await getEmployeesPaginated(page, PAGE_SIZE);
      const data = res.data;
      setEmployees(data.content);
      setAllEmployees(data.content);
      setTotalElements(data.totalElements);
      setTotalPages(data.totalPages);
      setCurrentPage(data.number);

      // Build unique departments list from current page
      const depts = [...new Set(data.content.map((e) => e.department))].sort();
      setDepartments(depts);
    } catch (err) {
      console.error('Failed to fetch employees:', err);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => { fetchPage(0); }, [fetchPage]);

  // ─── Department filter (API call) ─────────────────────────────────────────
  useEffect(() => {
    if (!selectedDept) {
      fetchPage(0);
      return;
    }
    const filter = async () => {
      setLoading(true);
      try {
        const res = await searchByDepartment(selectedDept);
        setEmployees(res.data);
        setAllEmployees(res.data);
        setTotalElements(res.data.length);
        setTotalPages(1);
        setCurrentPage(0);
      } catch (err) {
        console.error('Department filter failed:', err);
      } finally {
        setLoading(false);
      }
    };
    filter();
  }, [selectedDept, fetchPage]);

  // ─── Client-side search filter ────────────────────────────────────────────
  const filteredEmployees = searchQuery.trim()
    ? allEmployees.filter((e) => {
        const q = searchQuery.toLowerCase();
        return (
          e.firstName?.toLowerCase().includes(q) ||
          e.lastName?.toLowerCase().includes(q) ||
          e.email?.toLowerCase().includes(q) ||
          e.department?.toLowerCase().includes(q)
        );
      })
    : employees;

  // ─── Pagination handler ───────────────────────────────────────────────────
  const handlePageChange = (newPage) => {
    if (newPage < 0 || newPage >= totalPages) return;
    setSearchQuery('');
    fetchPage(newPage);
  };

  // ─── Modal handlers ───────────────────────────────────────────────────────
  const handleAddClick = () => {
    setEditEmployee(null);
    setModalOpen(true);
  };

  const handleEditClick = (employee) => {
    setEditEmployee(employee);
    setModalOpen(true);
  };

  const handleModalClose = () => {
    setModalOpen(false);
    setEditEmployee(null);
  };

  const handleModalSubmit = async (formData) => {
    if (editEmployee) {
      await updateEmployee(editEmployee.id, formData);
    } else {
      await createEmployee(formData);
    }
    fetchPage(currentPage);
  };

  // ─── Delete handler ───────────────────────────────────────────────────────
  const handleDelete = async (id) => {
    if (!window.confirm('Delete this employee?')) return;
    try {
      await deleteEmployee(id);
      fetchPage(currentPage);
    } catch (err) {
      console.error('Delete failed:', err);
    }
  };

  // ─── Render ───────────────────────────────────────────────────────────────
  return (
    <div className="min-h-screen bg-gray-100 font-sans">
      {/* Header */}
      <Header />

      {/* Main Card */}
      <main className="max-w-7xl mx-auto px-4 sm:px-6 py-6">
        <div className="bg-white rounded-xl shadow-sm border border-gray-200 overflow-hidden">

          {/* Search + Filter + Add */}
          <SearchBar
            searchQuery={searchQuery}
            onSearchChange={setSearchQuery}
            selectedDept={selectedDept}
            onDeptChange={(d) => { setSelectedDept(d); setSearchQuery(''); }}
            departments={departments}
            onAddClick={handleAddClick}
          />

          {/* Employee Table */}
          <EmployeeTable
            employees={filteredEmployees}
            onEdit={handleEditClick}
            onDelete={handleDelete}
            loading={loading}
          />

          {/* Pagination */}
          {!searchQuery && (
            <Pagination
              currentPage={currentPage}
              totalPages={totalPages}
              totalElements={totalElements}
              pageSize={PAGE_SIZE}
              onPageChange={handlePageChange}
            />
          )}
        </div>
      </main>

      {/* Add / Edit Modal */}
      <EmployeeModal
        isOpen={modalOpen}
        onClose={handleModalClose}
        onSubmit={handleModalSubmit}
        employee={editEmployee}
      />
    </div>
  );
}
