package nl.macemuilman.privatenotes;

import com.thoughtworks.xstream.XStream;
import java.util.List;

public class NoteService {
    public static final NoteService instance = new NoteService();

    private NoteService() {
        this.notes = this.get();
    }

    NotesList notes = new NotesList();

    public List all() {
        return this.notes.all();
    }

    public Note findById(int noteId) {
        return this.notes.findById(noteId);
    }

    public void add(String title, String content) {
        Note note = new Note();

        note.setTitle(title);
        note.setContent(content);

        this.notes.add(note);
        this.save();
    }

    public void update(int id, String title, String content) {
        Note note = new Note();

        note.setTitle(title);
        note.setContent(content);

        this.notes.update(id, note);
        this.save();
    }

    public void delete(int noteId) {
        this.notes.delete(noteId);
        this.save();
    }

    public void save () {
        XStream xstream = new XStream();
        xstream.alias("note", Note.class);
        xstream.alias("notes", NotesList.class);
        xstream.addImplicitCollection(NotesList.class, "notes");

        String xml = xstream.toXML(this.notes);

        Constants.instance().storeValueString("NOTES", xml);
    }

    public NotesList get() {
        String xml = (Constants.instance().fetchValueString("NOTES"));

        if (xml != null) {
            XStream xstream = new XStream();
            xstream.alias("note", Note.class);
            xstream.alias("notes", NotesList.class);
            xstream.addImplicitCollection(NotesList.class, "notes");
            NotesList notesList = (NotesList)xstream.fromXML(xml);

            return notesList;
        }

        NotesList notesList = new NotesList();
        return notesList;
    }
}
