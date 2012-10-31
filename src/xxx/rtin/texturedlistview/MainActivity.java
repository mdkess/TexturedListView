package xxx.rtin.texturedlistview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        List<String> items = new ArrayList<String>(50);
        for(int i=0; i<50;++i) {
            items.add("Item #" + i);
        }
        
        TexturedListView listView = (TexturedListView) findViewById(R.id.texturedListView);
        
        listView.setAdapter(new CustomArrayAdapter(this, items));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
