/**
 * Header — App header bar
 * Reusable: accepts title and subtitle as props
 */

const Header = ({
  title = 'Employee Management System',
  subtitle = 'View, search, and manage all employee records',
}) => {
  return (
    <header className="bg-[#1e3a5f] px-8 py-6 shadow-md">
      <h1 className="text-2xl font-bold text-white tracking-tight">{title}</h1>
      <p className="text-sm text-blue-200 mt-1">{subtitle}</p>
    </header>
  );
};

export default Header;
