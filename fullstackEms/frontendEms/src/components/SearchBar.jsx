/**
 * SearchBar — Search input + department filter + Add Employee button
 * Reusable: passes all state up via props
 */

import Button from './ui/Button.jsx';
import SelectField from './ui/SelectField.jsx';

const SearchBar = ({
  searchQuery,
  onSearchChange,
  selectedDept,
  onDeptChange,
  departments = [],
  onAddClick,
}) => {
  const deptOptions = [
    { value: '', label: 'All departments' },
    ...departments.map((d) => ({ value: d, label: d })),
  ];

  return (
    <div className="flex flex-col sm:flex-row gap-3 items-start sm:items-center justify-between px-6 py-4">
      {/* Search Input */}
      <input
        type="text"
        value={searchQuery}
        onChange={(e) => onSearchChange(e.target.value)}
        placeholder="Search by name, email or department..."
        className="flex-1 w-full sm:max-w-md rounded-md border border-gray-300 bg-white px-3 py-2 text-sm text-gray-900 placeholder-gray-400 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition-colors"
      />

      <div className="flex gap-3 items-center w-full sm:w-auto">
        {/* Department Filter */}
        <SelectField
          id="dept-filter"
          value={selectedDept}
          onChange={(e) => onDeptChange(e.target.value)}
          options={deptOptions}
          placeholder=""
          className="min-w-[160px]"
        />

        {/* Add Button */}
        <Button variant="primary" size="md" onClick={onAddClick}>
          + Add Employee
        </Button>
      </div>
    </div>
  );
};

export default SearchBar;
