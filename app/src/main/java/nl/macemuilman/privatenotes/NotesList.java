package nl.macemuilman.privatenotes;

import java.util.ArrayList;
import java.util.List;

public class NotesList {
    private List<Note> notes;

    public NotesList() {
        notes = new ArrayList<Note>();
    }

    public void add(Note note) {
        notes.add(note);
    }

    public void update(int id, Note note) {
        this.notes.set(id, note);
    }

    public void delete(int noteId) {
        this.notes.remove(noteId);
    }

    public List<Note> all() {
        return this.notes;
    }

    public Note findById(int i) {
        return this.notes.get(i);
    }
}
