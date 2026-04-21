/**
 * SelectField — Reusable labeled select/dropdown component
 * Props: id, label, value, onChange, options (array of {value, label}), required, error
 */

const SelectField = ({
  id,
  label,
  value,
  onChange,
  options = [],
  placeholder = 'Select...',
  required = false,
  error = '',
  className = '',
}) => {
  return (
    <div className={`flex flex-col gap-1 ${className}`}>
      {label && (
        <label htmlFor={id} className="text-sm font-medium text-gray-700">
          {label}
          {required && <span className="text-red-500 ml-1">*</span>}
        </label>
      )}
      <select
        id={id}
        value={value}
        onChange={onChange}
        required={required}
        className={`
          w-full rounded-md border px-3 py-2 text-sm
          text-gray-900 bg-white
          focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500
          transition-colors duration-150 cursor-pointer
          ${error ? 'border-red-400 bg-red-50' : 'border-gray-300'}
        `}
      >
        {placeholder && (
          <option value="">{placeholder}</option>
        )}
        {options.map((opt) => (
          <option key={opt.value} value={opt.value}>
            {opt.label}
          </option>
        ))}
      </select>
      {error && <span className="text-xs text-red-500">{error}</span>}
    </div>
  );
};

export default SelectField;
