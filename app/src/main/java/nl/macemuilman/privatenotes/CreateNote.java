package nl.macemuilman.privatenotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class CreateNote extends AppCompatActivity {

    int note = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
    }

    protected void onStart() {
        super.onStart();

        Intent noteIntent = getIntent();
        this.note = noteIntent.getIntExtra("NOTE_ID", -1);

        TextView mainLabel = findViewById(R.id.mainLabel);
        FloatingActionButton deleteButton = findViewById(R.id.deleteNoteButton);
        if (this.note != -1) {
            Note note = NoteService.instance.findById(this.note);

            TextInputEditText noteTitle = findViewById(R.id.noteTitle);
            EditText noteContent = findViewById(R.id.noteContent);

            mainLabel.setText("Notitie aanpassen");
            deleteButton.show();
            noteTitle.setText(note.getTitle());
            noteContent.setText(note.getContent());
        } else {
            mainLabel.setText("Notitie aanmaken");
            deleteButton.hide();
        }
    }

    public void saveNote(View view) {
        TextInputEditText noteTitle = findViewById(R.id.noteTitle);
        EditText noteContent = findViewById(R.id.noteContent);

        if (this.note != -1) {
            NoteService.instance.update(this.note, noteTitle.getText().toString(), noteContent.getText().toString());
        } else {
            NoteService.instance.add(noteTitle.getText().toString(), noteContent.getText().toString());
        }

        this.switchToMainActivity();
    }

    public void deleteNote (View view) {
        if (this.note != -1) {
            NoteService.instance.delete(this.note);
        }

        this.switchToMainActivity();
    }

    protected void switchToMainActivity() {
        Intent MainIntent = new Intent(this, MainActivity.class);
        startActivity(MainIntent);
    }
}
