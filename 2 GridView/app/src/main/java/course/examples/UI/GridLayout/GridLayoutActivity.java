package course.examples.UI.GridLayout;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//This application uses some deprecated methods. 
//See UIViewPager for a more modern version of this application

public class GridLayoutActivity extends Activity {

	protected static final String EXTRA_RES_ID = "POS";
	private Context context;
	public static final String EXTRA_MESSAGE = "com.example.factActivity.MESSAGE";

	private int imageResource;
	private String[] names = {"China Tiger", "Bengal Tiger", "Lion", "Kangaroo", "Polar Bear", "Cheetah", "Dog", "Wolf", "Cat", "Camel", "Monkey", "Elephant", "Giraffe", "Giant Panda", "Rabbit"};
	private ArrayList<String> mNames = new ArrayList<>(Arrays.asList("China Tiger", "Bengal Tiger", "Lion", "Kangaroo", "Polar Bear", "Cheetah", "Dog", "Wolf", "Cat", "Camel", "Monkey", "Elephant", "Giraffe", "Giant Panda", "Rabbit"));

	private ArrayList<Integer> mThumbIdsFlowers = new ArrayList<Integer>(
			Arrays.asList(R.drawable.image1, R.drawable.image2,
					R.drawable.image3, R.drawable.image4, R.drawable.image5,
					R.drawable.image6, R.drawable.image7, R.drawable.image8,
					R.drawable.image9, R.drawable.image10, R.drawable.image11,
					R.drawable.image12, R.drawable.image13, R.drawable.image14,
					R.drawable.image15));

	private ArrayList<String> animal_wiki = new ArrayList<>(
			Arrays.asList("https://en.wikipedia.org/wiki/South_China_tiger", "https://en.wikipedia.org/wiki/Bengal_tiger", "https://en.wikipedia.org/wiki/Lion", "https://en.wikipedia.org/wiki/Kangaroo", "https://en.wikipedia.org/wiki/Polar_bear", "https://en.wikipedia.org/wiki/Cheetah", "https://en.wikipedia.org/wiki/Dog", "https://en.wikipedia.org/wiki/Wolf", "https://en.wikipedia.org/wiki/Cat", "https://en.wikipedia.org/wiki/Camel", "https://en.wikipedia.org/wiki/Monkey", "https://en.wikipedia.org/wiki/Elephant", "https://en.wikipedia.org/wiki/Giraffe", "https://en.wikipedia.org/wiki/Giant_panda", "https://en.wikipedia.org/wiki/Rabbit")
	);

	private ArrayList<String> animal_fact = new ArrayList<>(
			Arrays.asList("The South China Tiger is native to southern China. It is possibly extinct in the wild since no wild individual has been recorded since the late 1980s",
					"The Royal Bengal Tiger ranks among the biggest wild cats alive today. The tiger is estimated to have been present in the Indian subcontinent for about 12,000 to 16,500 years",
					"Lion is King of the Jungle. Lion is one of the most widely recognised animal symbols in human culture, the lion has been extensively depicted in sculptures and paintings, on national flags, and in contemporary films and literature",
					"Kangaroos are indigenous to Australia and New Guinea. They have large, powerful hind legs, large feet adapted for leaping, a long muscular tail for balance, and a small head. ",
					"The polar bear's native range lies largely within the Arctic Circle.  It is the largest extant bear species, as well as the largest extant land carnivore.",
					"Cheetah is the fastest land animal. Cheetah cubs are highly vulnerable to predation by other large carnivores such as hyenas and lions.",
					"The Dogs has their long association with humans. They perform many roles for humans, such as hunting, herding, pulling loads, protection, assisting police and the military, companionship, therapy, and aiding disabled people.",
					"The Wolf is most specialized for cooperative game hunting. Single wolves or mated pairs typically have higher success rates in hunting than do large packs.",
					"The Cat is a domestic species of a small carnivorous mammal. As of 2017, the domestic cat was the second-most popular pet in the United States.",
					"Camels are working animals especially suited to their desert habitat and are a vital means of transport for passengers and cargo. The camel's thick coat insulates it from the intense heat radiated from desert sand.",
					"Monkeys are generally considered to be intelligent, especially the Old World monkeys. A number of countries have used monkeys as part of their space exploration programmes, including the United States and France. The first monkey in space was Albert II, who flew in the US-launched V-2 rocket on June 14, 1949.",
					"Elephants are the largest existing land animals.  Their trunk is used for breathing, bringing food and water to the mouth, and grasping objects.",
					"Giraffe is the tallest living terrestrial animal and the largest ruminant on Earth. Giraffe's chief distinguishing characteristics are its extremely long neck and legs, its horn-like ossicones, and its spotted coat patterns.",
					"Giant Panda is also known as Panda Bear. The giant panda lives in a few mountain ranges in central China, mainly in Sichuan. It is becoming widely used within China in international contexts, for example, appearing since 1982 on gold panda bullion coins and as one of the five Fuwa mascots of the Beijing Olympics.",
					"Rabbits, also known as bunnies have long been domesticated. With its widespread effect on ecologies and cultures, the rabbit is, in many areas of the world, a part of daily life—as food, clothing, a companion, and a source of artistic inspiration.")
	);

	//	private final String[] animal_wiki = {"https://en.wikipedia.org/wiki/South_China_tiger", "https://en.wikipedia.org/wiki/Bengal_tiger", "https://en.wikipedia.org/wiki/Lion", "https://en.wikipedia.org/wiki/Kangaroo", "https://en.wikipedia.org/wiki/Polar_bear", "https://en.wikipedia.org/wiki/Cheetah", "https://en.wikipedia.org/wiki/Dog", "https://en.wikipedia.org/wiki/Wolf", "https://en.wikipedia.org/wiki/Cat", "https://en.wikipedia.org/wiki/Camel", "https://simple.wikipedia.org/wiki/Vervet_monkey", "https://en.wikipedia.org/wiki/Elephant"};

	private ArrayList<Integer> facts = new ArrayList<Integer>(
			Arrays.asList(R.drawable.image1, R.drawable.image2,
					R.drawable.image3, R.drawable.image4, R.drawable.image5,
					R.drawable.image6, R.drawable.image7, R.drawable.image8,
					R.drawable.image9, R.drawable.image10, R.drawable.image11));

	public ArrayList<Animal> animals;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		animals = new ArrayList<>();

		setContentView(R.layout.main);

		GridView gridview = (GridView) findViewById(R.id.gridview);

		// Create a new ImageAdapter and set it as the Adapter for this GridView
		gridview.setAdapter(new ImageAdapter(this, mThumbIdsFlowers, mNames));

		// Set an setOnItemClickListener on the GridView
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
									int position, long id) {

				//Create an Intent to start the ImageViewActivity
				String temp=animal_fact.get(position);
				Intent intent = new Intent(GridLayoutActivity.this,
						ImageViewActivity.class);

				// Add the ID of the thumbnail to display as an Intent Extra
				intent.putExtra(EXTRA_RES_ID, (int) id);
				intent.putExtra("position", (int) position);

				// Start the ImageViewActivity
				startActivity(intent);
			}
		});
		registerForContextMenu(gridview);

		gridview.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				Intent intent = new Intent(GridLayoutActivity.this, ImageViewActivity.class);
				startActivity(intent);
				return true;
			}
		});
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.context_menu, menu);

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		GridView gridview = (GridView) findViewById(R.id.gridview);
		switch (item.getItemId()) {
			case R.id.full_image:
				Intent intent = new Intent(GridLayoutActivity.this, ImageViewActivity.class);
				intent.putExtra(EXTRA_RES_ID, (int) info.id);
				startActivity(intent);
				return false;
			case R.id.facts:
				String message = animal_fact.get((int) info.position);
				Intent fact_intent = new Intent(GridLayoutActivity.this, Facts.class);
				fact_intent.putExtra("Facts",message);
				//Toast.makeText(GridLayoutActivity.this, "" + message, Toast.LENGTH_SHORT).show();
				startActivity(fact_intent);
				// Capture the layout's TextView and set the string as its text
				return false;
			case R.id.wiki:
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(animal_wiki.get((int) info.position)));
				startActivity(browserIntent);
				return false;
			default:
				return super.onContextItemSelected(item);
		}

	}
}