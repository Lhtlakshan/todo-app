
import { Toaster } from 'react-hot-toast'
import './App.css'
import ToDo from './ToDo'

function App() {

  return (
    <>
    
    <Toaster
  position="top-right"
  reverseOrder={false}
/>
      <div className='w-full h-full'>
        <ToDo/>
      </div>
    </>
  )
}

export default App
