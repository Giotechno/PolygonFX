import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.SVGPath;

public class PolygonFX{

	/*****************************************************/
	/*****************************************************/
	private final Scene field;
	private boolean KeyPressed = false;
	private final Pane Container;
	private ArrayList<String> Typage = new ArrayList<>();
    private int IDRank = 0;
	private SVGPath Forme = new SVGPath();
	private MouseEvent Coordinate;
	private boolean InitialC = false;
	
    /*****************************************************/
	/*****************************************************/
	
	private ArrayList<SVGPath> Connecteurs = new ArrayList<>();
	private double xOffset = 0;
	private double yOffset = 0;
	private final String circle = "M0 5 A5 5 0 1 1 10 5 M0 5 A5 5 0 1 0 10 5";
	
	/*****************************************************/
	/*****************************************************/
	
	
    public PolygonFX(Scene root){
		
	    field = root;
	    // Change depending on package
	    field.getStylesheets().add(getClass().getResource("PolygonStyle.css").toExternalForm());
	    Container = (Pane) field.getRoot();
	    MouseNKey();
		
	}

    private void Create(){

		Forme.getStyleClass().add("forme");
		Forme.setContent("M" + Coordinate.getX() + " " + Coordinate.getY() + " ");
		Typage.add("M" + Coordinate.getX() + " " + Coordinate.getY() + " ");
		Container.getChildren().addAll(Forme);
		
    }
	
    /*****************************************************/
	/*****************************************************/
    
    private void MouseNKey(){
    	
		field.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				if(event.getCode() == KeyCode.D){
					
					KeyPressed = true;
	
				}
				
                if(event.getCode() == KeyCode.S){
					
                	KeyPressed = false;
                	Finaliser();
					
				}
                   
                if(event.getCode() == KeyCode.ENTER){

                	DeleteConnects();
					
				}
   
			}
		});
		
		
		field.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				   if((!InitialC) && KeyPressed){

				       Create();
				       InitialC=true;

				   }

				   if(KeyPressed){
	   
					   RemoveZ();
					   PointCreator(); 
				       Typage.add("L" +Coordinate.getX() + " " + Coordinate.getY() + " " + "Z");			       
				       IDRank++; 
				       
				   }

			}
		});
		
	
		field.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				Coordinate = event;
			
				if(KeyPressed && InitialC){
					 
		              Update(Coordinate.getX(), Coordinate.getY());

		        }
				
			}
		});

	}

    /*****************************************************/
	/*****************************************************/
 
    private void Update(double x, double y){

        Typage.set(IDRank,"L" +x + " " + y + " " + "Z ");

        Forme.setContent(ToContent());

    }
    
    /*****************************************************/
	/*****************************************************/
    
    private String ToContent(){

    	String content = "";

    	for(String s: Typage){

    	content+=s;

    	}

    	return content;
    }
    
    /*****************************************************/
	/*****************************************************/
    
    private void RemoveZ(){
   
    	String s = Typage.get(IDRank).replace("Z", ""); 
    	Typage.set(IDRank, s);

    }
    
    /*****************************************************/
	/*****************************************************/
    
    private void Finaliser(){
    	
    	Typage.set(IDRank, "Z ");
    	Forme.setContent(ToContent());
    	
    }
    
    
    /*****************************************************/
	/*****************************************************/
    
    private void PointCreator(){

		
		SVGPath Connect = new SVGPath();
		Connect.setContent(circle);
		Connect.getStyleClass().add("pointeur");
		Connect.setLayoutX(Coordinate.getX()-5);
		Connect.setLayoutY(Coordinate.getY()-5);
		
		Connecteurs.add(Connect); 
        Container.getChildren().add(Connect);
        ControlEvt(Connect);
        
	}
    
    /*****************************************************/
	/*****************************************************/
    
    private void ControlEvt(SVGPath Connect){

    	Connect.setId(String.valueOf(IDRank)); 
    	
    	Connect.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				Connect.setLayoutX(event.getSceneX() - xOffset);
				Connect.setLayoutY(event.getSceneY() - yOffset);
				Updater(Integer.valueOf(Connect.getId()), Connect.getLayoutX()+5, Connect.getLayoutY()+5); 
			
			}
		});
    	Connect.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
	
				 xOffset = event.getX();
	             yOffset = event.getY();
				
			}
		});
		
		
	}
    
    
    /*****************************************************/
	/*****************************************************/
    
    private void Updater(int ID,double x,double y){

    	Typage.set(ID, getModifable(ID,x,y));
    	Forme.setContent(ToContent());
    	
    }

    /*****************************************************/
	/*****************************************************/

    private String getModifable(int index,double x, double y){
    	 
    	 String returned = "";
    	 
    	 if(Typage.get(index).startsWith("L") && Typage.get(index).endsWith("Z")){ 
    			
    		returned = "L" + String.valueOf(x) + " " + String.valueOf(y) + " " + "Z";
    	  
    	 }
    	        
    	 if(Typage.get(index).startsWith("L")){
    			 
    	    returned = "L" + String.valueOf(x) + " " + String.valueOf(y) + " ";		 
    			
    	 }
    			
    	 if(Typage.get(index).startsWith("M")){
    				
    		returned = "M" + String.valueOf(x) + " " + String.valueOf(y) + " ";
    				
    	 }
    	 
    	 return returned;
    			
    }
    
    /*****************************************************/
	/*****************************************************/
    
    private void DeleteConnects(){
    	
    	Container.getChildren().removeAll(Connecteurs);
    	
    }
 
 
}
