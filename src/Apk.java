import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pl.kofun.mavis.Options;
import pl.kofun.mavis.Interfaces.MainTask;
import pl.kofun.mavis.tasks.MonthPlotter;
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
		// TODO Auto-generated method stub
		
		Button Mpbtn = new Button();
		Mpbtn.setText("Make month plot");
        Mpbtn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	options.setTask("Mp");
            	execute();
            }
        });
        
        Button Ypbtn = new Button();
        Ypbtn.setText("Make year plot");
        Ypbtn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	options.setTask("Yp");
            	execute();
            }
        });
        Ypbtn.setTranslateY(30);
        Ypbtn.setTranslateX(-5);
        
        StackPane root = new StackPane();
        root.getChildren().add(Mpbtn);
        root.getChildren().add(Ypbtn);

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
