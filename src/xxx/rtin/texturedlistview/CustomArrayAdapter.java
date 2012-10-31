package xxx.rtin.texturedlistview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<String> {
    public static class ViewHolder {
        TextView textView;
        int distanceFromTop;
    }
    List<Integer> mDistances;
    List<Integer> mHeights;
    
    public CustomArrayAdapter(Context context, List<String> objects) {
        //TODO: Unsure the purpose of the middle two parameters here.
        super(context, R.layout.list_item, R.id.listItemText, objects);
        mDistances = new ArrayList<Integer>();
        mHeights = new ArrayList<Integer>();
        for(int i=0; i<objects.size(); ++i) {
            mDistances.add(0);
            mHeights.add(0);
        }
    }
    
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View row = convertView;
        final String item = getItem(position);
        ViewHolder holder = null;
        if(row == null) {
              LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
              row = inflater.inflate(R.layout.list_item, parent, false);
              
              holder = new ViewHolder();
              holder.textView = (TextView) row.findViewById(R.id.listItemText);

              row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }
        
        final View ref = row;
        final ViewHolder holderRef = holder;
        
        row.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            public void onLayoutChange(View v, int left, int top, int right,
                    int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                ref.removeOnLayoutChangeListener(this);
                Log.d("CustomArrayAdapter", "onLayoutChange(" + position + "): height " + (bottom - top));
                mHeights.set(position, (bottom - top));
                if(position > 0) {
                    mDistances.set(position, bottom - top + mDistances.get(position-1) + ((ListView)parent).getDividerHeight());
                }
                holderRef.distanceFromTop = mDistances.get(position);
                Log.d("CustomArrayAdapter", "New height for " + position + " is " + mHeights.get(position) + " Distance: " + mDistances.get(position));
            }
        });

        holder.textView.setText(item);
        holder.distanceFromTop = mDistances.get(position);
        
        return row;
    }
}
