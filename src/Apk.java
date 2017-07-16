import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pl.kofun.mavis.Options;
import pl.kofun.mavis.Interfaces.MainTask;
import pl.kofun.mavis.TaskFactory;


public class Apk extends Application{

	public static Options options;
	public MainTask task;
	
	public static void main(String args[])
	{		
		options = new Options(args);
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Button Mpbtn = new Button();
		Mpbtn.setText("Make month plot");
        Mpbtn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	options.setTask("Mp");
            	execute();
            }
        });
        Mpbtn.setTranslateY(-90);
        Mpbtn.setTranslateX(-45);
        
        Button Ypbtn = new Button();
        Ypbtn.setText("Make year plot");
        Ypbtn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	options.setTask("Yp");
            	execute();
            }
        });
        Ypbtn.setTranslateY(-60);
        Ypbtn.setTranslateX(-50);
        
        Button Ohilbtn = new Button();
        Ohilbtn.setText("Make one hundred years in library");
        Ohilbtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				options.setTask("Ohil");
				execute();
			}
		});
        Ohilbtn.setTranslateY(-30);
        
        Button Psbtn = new Button();
        Psbtn.setText("Make project post!");
        Psbtn.setOnAction(new EventHandler<ActionEvent>(){
        	public void handle(ActionEvent event){
        		options.setTask("Ps");
        		execute();
        	}
        });
        Psbtn.setTranslateX(-40);
        
        StackPane root = new StackPane();
        root.getChildren().add(Mpbtn);
        root.getChildren().add(Ypbtn);
        root.getChildren().add(Ohilbtn);
        root.getChildren().add(Psbtn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Mavis");
        primaryStage.setScene(scene);
        primaryStage.show();
		
		if(options.containsKey("save"))
		{
			options.save();
		}
	}
	
	public void execute()
	{
		task = TaskFactory.CreateTask(options);
		task.execute();
	}
}
