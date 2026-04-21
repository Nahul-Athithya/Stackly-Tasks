/**
 * Pagination — Shows page info and Prev/Next controls
 * Reusable: fully prop-driven with no internal state
 */

import Button from './ui/Button.jsx';

const Pagination = ({
  currentPage,
  totalPages,
  totalElements,
  pageSize,
  onPageChange,
}) => {
  const showing = Math.min((currentPage + 1) * pageSize, totalElements);
  const from = totalElements === 0 ? 0 : currentPage * pageSize + 1;

  return (
    <div className="flex flex-col sm:flex-row items-center justify-between gap-3 px-5 py-3.5 border-t border-gray-200 text-sm text-gray-500">
      {/* Left: showing count */}
      <span>
        Showing <span className="font-medium text-gray-700">{from}–{showing}</span> of{' '}
        <span className="font-medium text-gray-700">{totalElements}</span>
      </span>

      {/* Right: page info + controls */}
      <div className="flex items-center gap-3">
        <Button
          variant="outline"
          size="sm"
          onClick={() => onPageChange(currentPage - 1)}
          disabled={currentPage === 0}
        >
          ← Prev
        </Button>

        <span>
          Page{' '}
          <span className="font-medium text-gray-700">{currentPage + 1}</span>{' '}
          of{' '}
          <span className="font-medium text-gray-700">
            {totalPages === 0 ? 1 : totalPages}
          </span>
        </span>

        <Button
          variant="outline"
          size="sm"
          onClick={() => onPageChange(currentPage + 1)}
          disabled={currentPage >= totalPages - 1}
        >
          Next →
        </Button>
      </div>
    </div>
  );
};

export default Pagination;
