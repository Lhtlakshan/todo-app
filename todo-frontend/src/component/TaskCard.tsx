import React from "react";

type TaskCardProps = {
  title: string;
  description: string;
  isDone: boolean;
  onDoneClick: () => void;
};

const TaskCard: React.FC<TaskCardProps> = ({ title, description, isDone, onDoneClick }) => {
  return (
    <div className="w-full max-w-md p-5 bg-gray-300 rounded-lg shadow-xl flex justify-between items-center m-[20px]">
      <div>
        <h2 className=" font-bold text-xl">{title}</h2>
        <p className="text-sm text-gray-700 pt-3">{description}</p>
      </div>
      <button
        onClick={onDoneClick}
        className="px-4 py-1 mt-8 w-[100px] border rounded bg-gray-300 border-black text-[12px] cursor-pointer
         hover:bg-gray-500 hover:text-white hover:border-none"
      >
        Done
      </button>
    </div>
  );
};

export default TaskCard;
