package cs111.project1;

import java.util.List;

import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
public abstract class Framework extends Application {
    protected static final String
    	STYLE_EXAMINE	= "-fx-background-color: #005EB8; -fx-text-fill: #FFFFFF;",
    	STYLE_FOUND		= "-fx-background-color: #EAAA00; -fx-text-fill: #FFFFFF; -fx-underline: true;",
		STYLE_MISMATCH	= "-fx-background-color: #A2AAAD;",
    	STYLE_NOT_FOUND	= "-fx-background-color: #E35205; -fx-text-fill: #FFFFFF;";

    private static final int DELAY_MILLIS = 250;
    
    
    
    private static final String[] ALGORITHMS = {
    	"Linear",
    	"Binary",
    	"Random",
    	"Fibonacci",
    	"Jump Search"
    };
    
    private List<Person> people;
    private List<Label> labels;
    
    @Override
	public void init() {
    	people = generate();
    }
    
    @Override
    public void start(Stage stage) {
    	final VBox box; {
    		box = new VBox();
	    	box.setPadding(new Insets(20));
	    	box.setSpacing(10);
	    	box.setStyle("-fx-background-color: #FFFFFF;");
    	
    		final FlowPane list; {
    			list = new FlowPane(Orientation.VERTICAL);
	    		list.setVgap(5);
	    		list.setHgap(35);
    		
	    		labels = new ArrayList<Label>();
		    	for (Person p: people) {
		    		Label entry = new Label(p.toString());
		    		entry.setFont(new Font(null, 18));
		    		labels.add(entry);
		    		list.getChildren().add(entry);
		    	}
    		}
	    	
	    	final HBox bar; {
	    		bar = new HBox();
	    		bar.setSpacing(10);
	    	
		    	final TextField given = new TextField();
		    	given.setPromptText("Given Name");
		    	
		    	final TextField family = new TextField();
		    	family.setPromptText("Family Name");
			    	
		    	final HBox buttons; {
		    		buttons = new HBox();
			    	buttons.setSpacing(10);
				    buttons.setDisable(true);
			    	
			    	final List<Button> searches = new ArrayList<Button>();
			    	for (String algo: ALGORITHMS) {
			    		Button button = new Button(algo);
			    		button.setOnAction(event -> search(algo, given, family, bar));
			    		searches.add(button);
			    	}
			    	
			    	buttons.disableProperty().bind(Bindings.or(
		    			given.textProperty().isEmpty(),
		    			family.textProperty().isEmpty()
			    	));
			    	
			    	buttons.getChildren().addAll(searches);
		    	}
		    	
			    bar.getChildren().addAll(given, family, buttons);
	    	}
	    	
			box.getChildren().addAll(list, bar);
    	}
    	
    	stage.setTitle("Animated Search");
    	stage.setScene(new Scene(box));
    	stage.setResizable(false);
    	stage.show();
    }
    
	private void search(String type, TextField given, TextField family, Node controls) {
    	new Thread(() -> {
			paintAll(null);
			
			controls.setDisable(true);

			final Person target = new MyPerson(given.getText(), family.getText());
	    	if (type.equals("Linear"))
				runLinearSearch(target, people);
	    	else if (type.equals("Binary"))
				runBinarySearch(target, people);
	    	else if (type.equals("Random"))
				runRandomSearch(target, people);
	    	else if (type.equals("Fibonacci"))
				runFibonacciSearch(target, people);
	    	
			controls.setDisable(false);
    	}).start();
    }
    
    protected void paint(int index, String style) {
		labels.get(index).setStyle(style);
    }
    
    protected void paintAll(String style) {
		for (int i = 0; i < labels.size(); i++)
    		paint(i, style);
    }
    
    protected void delay() {
		try {
			Thread.sleep(DELAY_MILLIS);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    protected abstract void runLinearSearch(Person needle, List<Person> haystack);
    
    protected abstract void runBinarySearch(Person needle, List<Person> haystack);
    
    protected abstract void runRandomSearch(Person needle, List<Person> haystack);
    
    protected abstract void runFibonacciSearch(Person needle, List<Person> haystack);
    
	private static List<Person> generate() {
    	List<Person> list = new ArrayList<Person>();
    	list.add(new MyPerson("Angelique", "Haus"));
    	list.add(new MyPerson("Tashina", "Klingbeil"));
    	list.add(new MyPerson("Joni", "Kulig"));
    	list.add(new MyPerson("Sydney", "Mabon"));
    	list.add(new MyPerson("Dia", "Mehr"));
    	list.add(new MyPerson("Colton", "Jeong"));
    	list.add(new MyPerson("Ingrid", "Mansfield"));
    	list.add(new MyPerson("Lashell", "Wilkin"));
    	list.add(new MyPerson("Ashlea", "Belote"));
    	list.add(new MyPerson("Omer", "Lybrand"));
    	list.add(new MyPerson("Latonia", "Pruden"));
    	list.add(new MyPerson("Carlita", "Luter"));
    	list.add(new MyPerson("Valorie", "Eccles"));
    	list.add(new MyPerson("Vikki", "Halloran"));
    	list.add(new MyPerson("Sunny", "Schmitz"));
    	list.add(new MyPerson("Samual", "Schmit"));
    	list.add(new MyPerson("Valene", "Ryan"));
    	list.add(new MyPerson("Marinda", "Tousant"));
    	list.add(new MyPerson("Demetria", "Shevlin"));
    	list.add(new MyPerson("Ara", "Freeman"));
    	list.add(new MyPerson("Kathie", "Krok"));
    	list.add(new MyPerson("Tony", "Santibanez"));
    	list.add(new MyPerson("Kirstin", "Hsu"));
    	list.add(new MyPerson("Cesar", "McVey"));
    	list.add(new MyPerson("Alejandra", "Donner"));
    	list.add(new MyPerson("Shaina", "Liggins"));
    	list.add(new MyPerson("Regina", "Weimar"));
    	list.add(new MyPerson("Chloe", "Criswell"));
    	list.add(new MyPerson("Laquanda", "Lemieux"));
    	list.add(new MyPerson("Marguerita", "Vintner"));
    	list.add(new MyPerson("Wenona", "Bridge"));
    	list.add(new MyPerson("Sherilyn", "Davisson"));
    	list.add(new MyPerson("Rene", "Outman"));
    	list.add(new MyPerson("Everett", "Dingee"));
    	list.add(new MyPerson("Mozelle", "Parrent"));
    	list.add(new MyPerson("Arielle", "Hanrahan"));
    	list.add(new MyPerson("Nora", "Abshire"));
    	list.add(new MyPerson("Nanette", "Maisch"));
    	list.add(new MyPerson("Sumiko", "Bender"));
    	list.add(new MyPerson("Jann", "Lescarbeau"));
    	list.add(new MyPerson("Garret", "Davies"));
    	list.add(new MyPerson("Tanya", "Delilah"));
    	list.add(new MyPerson("Hulda", "Wirth"));
    	list.add(new MyPerson("Elden", "Lopinto"));
    	list.add(new MyPerson("Alecia", "Alvarez"));
    	list.add(new MyPerson("Lorita", "Brower"));
    	list.add(new MyPerson("Danuta", "Sarcone"));
    	list.add(new MyPerson("Valentin", "Lamoureux"));
    	Collections.sort(list);
    	return Collections.unmodifiableList(list);
    }
}