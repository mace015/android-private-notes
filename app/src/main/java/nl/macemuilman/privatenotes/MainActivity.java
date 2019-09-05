package nl.macemuilman.privatenotes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText note;
    String note_file_path;

    public void switchToCreateNewNoteView(View view)
    {
        Intent createNoteIntent = new Intent(this, CreateNote.class);
        startActivity(createNoteIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Constants.instance(this.getApplicationContext());
    }

    @Override
    protected void onStart() {
        super.onStart();

        List<Note> notes = NoteService.instance.all();

        List<String> noteTitles = new ArrayList<String>();
        for (Note note : notes) {
            noteTitles.add(note.getTitle());
        }
        ArrayAdapter<String> notesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, noteTitles);

        ListView notesList = (ListView)findViewById(R.id.notesList);
        notesList.setAdapter(notesAdapter);

        notesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent editNoteIntent = new Intent(getApplicationContext(), CreateNote.class);
                editNoteIntent.putExtra("NOTE_ID", i);
                startActivity(editNoteIntent);
            }
        });
    }
}
