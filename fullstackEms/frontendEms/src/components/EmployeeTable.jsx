/**
 * EmployeeTable — Renders the full data table with column headers
 * Reusable: receives employees array and action callbacks via props
 */

import EmployeeRow from './EmployeeRow.jsx';

const COLUMNS = ['ID', 'Name', 'Email', 'Salary', 'Department', 'Actions'];

const EmployeeTable = ({ employees = [], onEdit, onDelete, loading }) => {
  return (
    <div className="overflow-x-auto">
      <table className="w-full text-left border-collapse">
        {/* Table Head */}
        <thead>
          <tr className="bg-gray-50 border-b border-gray-200">
            {COLUMNS.map((col) => (
              <th
                key={col}
                className="px-5 py-3 text-xs font-semibold text-gray-500 uppercase tracking-wide"
              >
                {col}
              </th>
            ))}
          </tr>
        </thead>

        {/* Table Body */}
        <tbody>
          {loading ? (
            <tr>
              <td colSpan={6} className="text-center py-12 text-gray-400 text-sm">
                Loading employees...
              </td>
            </tr>
          ) : employees.length === 0 ? (
            <tr>
              <td colSpan={6} className="text-center py-12 text-gray-400 text-sm">
                No employees found.
              </td>
            </tr>
          ) : (
            employees.map((emp) => (
              <EmployeeRow
                key={emp.id}
                employee={emp}
                onEdit={onEdit}
                onDelete={onDelete}
              />
            ))
          )}
        </tbody>
      </table>
    </div>
  );
};

export default EmployeeTable;
