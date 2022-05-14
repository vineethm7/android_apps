package course.examples.UI.GridLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class ImageViewActivity extends Activity {

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

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	
		// Get the Intent used to start this Activity
		Intent intent = getIntent();
		
		// Make a new ImageView
		// Example of programmatic layout definition
		ImageView imageView = new ImageView(getApplicationContext());
		int position = getIntent().getExtras().getInt("position");
		// Get the ID of the image to display and set it as the image for this ImageView
		imageView.setImageResource(intent.getIntExtra(GridLayoutActivity.EXTRA_RES_ID, 0));

		setContentView(imageView);

		imageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String message=animal_fact.get(position);
				Intent intent = new Intent(ImageViewActivity.this, Facts.class);
				intent.putExtra("Facts",message);
				startActivity(intent);

			}
		});


	}
}