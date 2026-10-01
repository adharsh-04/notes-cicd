import React, { useState, useEffect } from "react";
import axios from "axios";

function App() {
  const [notes, setNotes] = useState([]);
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");

  useEffect(() => {
    axios.get("http://localhost:8080/api/notes")
        .then(res => setNotes(res.data));
  }, []);

  const addNote = () => {
    axios.post("http://localhost:8080/api/notes", { title, content })
        .then(res => setNotes([...notes, res.data]));
    setTitle("");
    setContent("");
  };

  const deleteNote = (id) => {
    axios.delete(`http://localhost:8080/api/notes/${id}`)
        .then(() => setNotes(notes.filter(note => note.id !== id)));
  };

  return (
      <div style={{ padding: "20px" }}>
        <h1>Notes App</h1>
        <input
            placeholder="Title"
            value={title}
            onChange={e => setTitle(e.target.value)}
        />
        <input
            placeholder="Content"
            value={content}
            onChange={e => setContent(e.target.value)}
        />
        <button onClick={addNote}>Add Note</button>

        <ul>
          {notes.map(note => (
              <li key={note.id}>
                <strong>{note.title}</strong>: {note.content}
                <button onClick={() => deleteNote(note.id)}>Delete</button>
              </li>
          ))}
        </ul>
      </div>
  );
}

export default App;
