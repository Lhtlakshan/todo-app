
import { useEffect, useState } from 'react'
import './App.css'
import TaskCard from './component/TaskCard.tsx'
import axios from 'axios';
import toast from 'react-hot-toast';
const ToDo = ()=> {

    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");

    const [tasksList, setTasksList] = useState([]);
    const [loading, setLoading] = useState(false);

    const getAllTasks = async ()=>{
        try{
           const res = await axios.get(import.meta.env.VITE_BACKEND_URL)
           setTasksList(res.data.data);
           console.log(res.data.data)
         }catch(err){
            toast.error("Tasks cannot be fetched")
         }
    }

    const addTask = async () => {
         try{
            await axios.post(import.meta.env.VITE_BACKEND_URL, 
           {
            title,description
        });
        setTitle("");
        setDescription("");
        setLoading(false)
        
        toast.success("Task added successfully")
         }catch(err){
            toast.error("Task cannot be added")
         }
    }

    const taskDone = async (id: number) => {
         try {
           await axios.patch(`${import.meta.env.VITE_BACKEND_URL}/${id}`,
            {
            isDone: true
            });
           toast.success("Task marked as done");
           setLoading(false);
         } catch (err) {
           toast.error("Failed to update task");
         }
    }


    useEffect(()=>{
        if(!loading){
            getAllTasks();
            setLoading(true)
        }
    },[loading])


  return (
    <div className="flex flex-row justify-between"> 
        <div className="bg-white/60 h-[80%] w-[50%] m-10 rounded-3xl p-8 flex flex-col items-center">
    
          <div className="flex flex-col items-start space-y-4">
            <h1 className="text-2xl font-bold">Add a Task</h1>
            <input
              onChange={(e) => setTitle(e.target.value)}
              type="text"
              className="w-[150%] h-10 border border-gray-400 rounded-lg p-2"
              placeholder="Title"
              required
            />
            <input
              onChange={(e) => setDescription(e.target.value)}
              type="text"
              className="w-[150%] h-24 border border-gray-400 rounded-lg p-2"
              placeholder="Description"
              required
            />

            <button
              type="submit"
              className="w-[30%] h-10 bg-blue-700 text-white rounded-lg hover:bg-blue-600 focus:bg-blue-800 active:bg-blue-900"
              onClick={addTask}
            >
              Add
            </button>
          </div>
        </div>

        <div className="w-[3px] bg-gray-400 fixed top-[40px] h-[80vh] left-1/2"></div>

        <div className='w-[55%] h-screen flex flex-col items-center mt-[60px]'>
           {
            tasksList.map(task=>(
                <TaskCard key={task.id} title={task.title} description={task.description} isDone={false} 
                onDoneClick={()=>taskDone(task.id)}/>
            ))
           }
        </div>
    </div>
  )
}

export default ToDo
