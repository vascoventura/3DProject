package java3dproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.AudioDevice;
import javax.media.j3d.Background;
import javax.media.j3d.BackgroundSound;
import javax.media.j3d.Billboard;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.ImageComponent;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Material;
import javax.media.j3d.MediaContainer;
import javax.media.j3d.PointLight;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.PositionInterpolator;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Screen3D;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Sound;
import javax.media.j3d.Text3D;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.audioengines.javasound.JavaSoundMixer;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.picking.PickResult;
import com.sun.j3d.utils.picking.PickTool;
import com.sun.j3d.utils.picking.behaviors.PickTranslateBehavior;
import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.universe.SimpleUniverse;

import appearance.MyMaterial;
import appearance.TextureAppearance;


public class Java3DProject extends Frame implements MouseListener, KeyListener, ActionListener {

	final float DISTANCE_SCALE = (float)  5/6963400;
	final float RADIUS = (float) 5/696340;
	
	private RotationInterpolator rotator = null;
	private PositionInterpolator translator = null;
	
	SimpleUniverse su;
	Transform3D viewTr;	
	 
	boolean enable = false;
	
	BackgroundSound bSound;
	
	Celestial solcel = new Celestial("Sol");
	Celestial mercuriocel = new Celestial("Mercúrio");
	Celestial venuscel = new Celestial("Vénus");
	Celestial terracel = new Celestial("Terra");
	Celestial luacel = new Celestial("Lua");
	Celestial martecel = new Celestial("Marte");
	Celestial jupitercel = new Celestial("Júpiter");
	Celestial saturnocel = new Celestial("Saturno");
	Celestial uranocel = new Celestial("Urano");
	Celestial neptunocel = new Celestial("Neptuno");
	
	float raio_sol = (float) solcel.getRaio() * RADIUS;
	
	float distancia_sol = (float) mercuriocel.getDistancia_sol() * DISTANCE_SCALE + 5;
	float raio_mercurio = (float) mercuriocel.getRaio() * RADIUS;
	
	float distancia_sol_venus = (float) venuscel.getDistancia_sol() * DISTANCE_SCALE + 5;
	float raio_venus = (float) venuscel.getRaio() * RADIUS;
	
	float distancia_sol_terra = (float) terracel.getDistancia_sol() * DISTANCE_SCALE + 5;
	float raio_terra = (float) terracel.getRaio() * RADIUS;
	
	float distancia_terra_lua = (float) luacel.getDistancia_sol() * DISTANCE_SCALE;
	float raio_lua = (float) luacel.getRaio() * RADIUS;
	
	float distancia_sol_marte = (float) martecel.getDistancia_sol() * DISTANCE_SCALE + 5;
	float raio_marte = (float) martecel.getRaio() * RADIUS;
	
	float distancia_sol_jupiter = (float) jupitercel.getDistancia_sol() * DISTANCE_SCALE + 5;
	float raio_jupiter = (float) jupitercel.getRaio() * RADIUS;
	
	float distancia_sol_saturno = (float) saturnocel.getDistancia_sol() * DISTANCE_SCALE + 5;
	float raio_saturno = (float) saturnocel.getRaio() * RADIUS;
	
	float distancia_sol_urano = (float) uranocel.getDistancia_sol() * DISTANCE_SCALE + 5; 
	float raio_urano = (float) uranocel.getRaio() * RADIUS;
	
	float distancia_sol_neptuno = (float) neptunocel.getDistancia_sol() * DISTANCE_SCALE + 5;
	float raio_neptuno = (float) neptunocel.getRaio() * RADIUS;
	
	//Pontos para a camara
	
	Point3d[][] pontos_camera = new Point3d[2][10];			
		
	TextArea ta;
	
	DirectionalLight dLight;
	
	int i = 0;
	
	BoundingSphere bounds = new BoundingSphere(new Point3d(0,0,0), 2000.0f); // Bounds of the scene
	PickCanvas pc;
	Canvas3D cv, offScreenCanvas;
	View view;
		
	
	
	public static void main(String[] args) {
		Frame frame = new Java3DProject();
		frame.setPreferredSize(new Dimension(1024,1024));
		frame.setTitle("Solar System 3D");
		frame.pack();
		frame.setVisible(true);
	}

	
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
		}
	}

	public Java3DProject() {
		setLayout(new BorderLayout());
	    ta = new TextArea("",3, 30, TextArea.SCROLLBARS_VERTICAL_ONLY);
	    ta.setEditable(false);
	    add(ta, BorderLayout.SOUTH);
	    
	  //Menus
  		MenuBar mb = new MenuBar();
  		this.setMenuBar(mb);
  		
  		//Item da lista do menu
  		
  		Menu menu = new Menu("File");
  		MenuItem mi = new MenuItem("Capture Image");
  		mi.addActionListener(this);
  		menu.add(mi);
  		mi = new MenuItem("Exit");
  		mi.addActionListener(this);
  		menu.add(mi);
  		mb.add(menu);
	    	    
	    GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
	    cv = new Canvas3D(gc);
	    add(cv, BorderLayout.CENTER);
	    cv.addMouseListener(this);
	    cv.addKeyListener(this);
	    
	   
	    
	    
	    offScreenCanvas = new Canvas3D(gc, true);
		Screen3D sOn = cv.getScreen3D();
	    Screen3D sOff = offScreenCanvas.getScreen3D();
	    Dimension dim = sOn.getSize();
	    sOff.setSize(dim);
	    sOff.setPhysicalScreenWidth(sOn.getPhysicalScreenWidth());
	    sOff.setPhysicalScreenHeight(sOn.getPhysicalScreenHeight());
	    Point loc = cv.getLocationOnScreen();
	    offScreenCanvas.setOffScreenLocation(loc);

	    
	    su = new SimpleUniverse(cv);
	    su.getViewingPlatform().setNominalViewingTransform();
	    BranchGroup bg = createSceneGraph(su, cv);
	    bg.compile();
	    su.addBranchGraph(bg);
	      
	    view = su.getViewer().getView();
	    
	    pontos_camera[0][0] = new Point3d(-0.0, 1.5, 30.0); //Sol
		pontos_camera[0][1] = new Point3d(distancia_sol, 0.0, 1.5);	//Mercurio
		pontos_camera[0][2] = new Point3d(distancia_sol_venus, 0.0, 1.5);	//Venus
		pontos_camera[0][3] = new Point3d(distancia_sol_terra, 0.0, 1.5); //Terra
		pontos_camera[0][4] = new Point3d(distancia_sol_terra, -2.0, 1.0);	//Lua
		pontos_camera[0][5] = new Point3d(distancia_sol_marte, 0.0, 1.5);	//Marte
		pontos_camera[0][6] = new Point3d(distancia_sol_jupiter, 0.0, 3.5);	//Jupiter
		pontos_camera[0][7] = new Point3d(distancia_sol_saturno, 0.4, 3.0);	//Saturno
		pontos_camera[0][8] = new Point3d(distancia_sol_urano, 0.0, 2.0);	//Urano
		pontos_camera[0][9] = new Point3d(distancia_sol_neptuno, 0.0, 2.0);	//Neptuno
		
		pontos_camera[1][0] = new Point3d(0.0, 0.0, 0.0); 
		pontos_camera[1][1] = new Point3d(distancia_sol, 0.0, 0.0);
		pontos_camera[1][2] = new Point3d(distancia_sol_venus, 0.0, 0.0);
		pontos_camera[1][3] = new Point3d(distancia_sol_terra, 0.0, 0.0);
		pontos_camera[1][4] = new Point3d(distancia_sol_terra, -2.0, 0.0);
		pontos_camera[1][5] = new Point3d(distancia_sol_marte, 0.0, 0.0);
		pontos_camera[1][6] = new Point3d(distancia_sol_jupiter, 0.0, 0.0);
		pontos_camera[1][7] = new Point3d(distancia_sol_saturno, 0.0, 0.0);
		pontos_camera[1][8] = new Point3d(distancia_sol_urano, 0.0, 0.0 );
		pontos_camera[1][9] = new Point3d(distancia_sol_neptuno, 0.0, 0.0);
	    
		
		//Vista
		viewTr = new Transform3D();
		viewTr.lookAt(pontos_camera[0][i], pontos_camera[1][i], new Vector3d(0,1,0));
		viewTr.invert();
		su.getViewingPlatform().getViewPlatformTransform().setTransform(viewTr);
		
		pc = new PickCanvas(cv, bg);
	    pc.setMode(PickTool.GEOMETRY);
		
		
		// Add a OrbitBehavior to control the first view with the mouse
		OrbitBehavior orbit = new OrbitBehavior(cv);
		orbit.setSchedulingBounds(bounds);
		su.getViewingPlatform().setViewPlatformBehavior(orbit);
		
		
		AudioDevice audioDev = new JavaSoundMixer(su.getViewer().getPhysicalEnvironment());
		audioDev.initialize();
		
	}
		
	
	

	private BranchGroup createSceneGraph(SimpleUniverse su, Canvas3D cv) {
		BranchGroup root = new BranchGroup();
		
		
		 PickTranslateBehavior translator_behavior = new PickTranslateBehavior(root, cv, bounds, PickTool.GEOMETRY);
		 root.addChild(translator_behavior);
		
		
		//Som
		bSound = new BackgroundSound();
		URL url = this.getClass().getClassLoader().getResource("sounds/shine.wav");
		MediaContainer mc = new MediaContainer(url);
		bSound.setSoundData(mc);
		bSound.setLoop(Sound.INFINITE_LOOPS);
		bSound.setSchedulingBounds(bounds);
		bSound.setInitialGain(0.01f);
		bSound.setEnable(true);
	
		root.addChild(bSound);		
		
		
		//Som 2
		bSound = new BackgroundSound();
		url = this.getClass().getClassLoader().getResource("sounds/beat.wav");
		mc = new MediaContainer(url);
		bSound.setSoundData(mc);
		bSound.setCapability(Sound.ALLOW_ENABLE_WRITE);
		bSound.setSchedulingBounds(bounds);
		bSound.setInitialGain(0.01f);
		bSound.setEnable(false);
	
		root.addChild(bSound);
				
		//Controlo da view com teclado
		TransformGroup vpTrans = null;
		
		vpTrans = su.getViewingPlatform().getViewPlatformTransform();
		
		
		KeyNavigatorBehavior keyNavBeh = new KeyNavigatorBehavior(vpTrans);
		keyNavBeh.setSchedulingBounds(bounds);
		PlatformGeometry platformGeom = new PlatformGeometry();
		platformGeom.addChild(keyNavBeh);
		su.getViewingPlatform().setPlatformGeometry(platformGeom);
		
		
		

		TransformGroup tg = new TransformGroup();
        TransformGroup tg2 = new TransformGroup();
        
        Alpha alpha = new Alpha();
        
        System.out.println("Constante: " + DISTANCE_SCALE);
	    System.out.println();
	    System.out.println();
	    
	    
        
		//Desenho dos Astros
	    //Sol
	    TextureAppearance solApp = new TextureAppearance("images/sun.png", false, this);		
		Sphere sol = new Sphere(raio_sol, Primitive.GENERATE_TEXTURE_COORDS, 50, solApp);
		sol.setName("Sol");
	    Transform3D tr = new Transform3D();
		tg = new TransformGroup(tr);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(sol);
		root.addChild(tg);
		
		
	    alpha = new Alpha(-1, 6000);
	    alpha.setMode(Alpha.INCREASING_ENABLE);
	    alpha.setDecreasingAlphaDuration(6000);
	    rotator = new RotationInterpolator(alpha, tg);
	    rotator.setSchedulingBounds(bounds);
	    rotator.setEnable(true);
	    root.addChild(rotator);
	    
	    
	  //Mercurio
  		Appearance mercurioApp = createAstro("images/mercurio.png");		
  	    Sphere mercurio = new Sphere(raio_mercurio, Primitive.GENERATE_TEXTURE_COORDS, 50, mercurioApp);
  	    mercurio.setName("Mercurio");
  	    tr = new Transform3D();
		tr.setTranslation(new Vector3f(distancia_sol, 0.0f, 0.0f));
		tg = new TransformGroup();
		tg2 = new TransformGroup(tr);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		tg.addChild(mercurio);
		tg2.addChild(tg);
		root.addChild(tg2);
		alpha = new Alpha(-1, 6000);
	    alpha.setMode(Alpha.INCREASING_ENABLE);
	    alpha.setDecreasingAlphaDuration(6000);
	    rotator = new RotationInterpolator(alpha, tg);
	    rotator.setSchedulingBounds(bounds);
	    rotator.setEnable(true);
	    root.addChild(rotator);
	 
	    System.out.println("Mercúrio: " + distancia_sol);
		    
	  //Venus
  		Appearance venusApp = createAstro("images/venus.jpeg");
  	    Sphere venus = new Sphere(raio_venus, Primitive.GENERATE_TEXTURE_COORDS, 50, venusApp);
	    venus.setName("Venus");
  	    tr = new Transform3D();
		tr.setTranslation(new Vector3f(distancia_sol_venus, 0.0f, 0.0f));
		tg = new TransformGroup();
		tg2 = new TransformGroup(tr);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);		
		tg.addChild(venus);
		tg2.addChild(tg);		
		root.addChild(tg2);	    
	    alpha = new Alpha(-1, 6000);
	    alpha.setMode(Alpha.INCREASING_ENABLE);
	    alpha.setDecreasingAlphaDuration(6000);
	    rotator = new RotationInterpolator(alpha, tg);
	    rotator.setSchedulingBounds(bounds);
	    rotator.setEnable(true);
	    root.addChild(rotator);
	    System.out.println("Venus: " + distancia_sol_venus);
	    
	    
	   
	  //Terra
  		TextureAppearance terraApp = new TextureAppearance("images/terra.JPG", false, this);
  	    Sphere terra = new Sphere(raio_terra, Primitive.GENERATE_TEXTURE_COORDS, 50, terraApp);
	    terra.setName("Terra");
  	    tr = new Transform3D();
		tr.setTranslation(new Vector3f(distancia_sol_terra, 0.0f, 0.0f));
		tg = new TransformGroup();
		tg2 = new TransformGroup(tr);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(terra);
		tg2.addChild(tg);
		root.addChild(tg2);	
	    alpha = new Alpha(-1, 6000);
	    alpha.setMode(Alpha.INCREASING_ENABLE);
	    alpha.setDecreasingAlphaDuration(6000);
	    rotator = new RotationInterpolator(alpha, tg);
	    rotator.setSchedulingBounds(bounds);
	    rotator.setEnable(true);
	    root.addChild(rotator);
	    System.out.println("Terra: " + distancia_sol_terra);
	    
	    
	  //Lua
  		Appearance luaApp = createAstro("images/moon.png");
  	    Sphere lua = new Sphere(raio_lua, Primitive.GENERATE_TEXTURE_COORDS, 50, luaApp);
	    lua.setName("Lua");
  	    tr = new Transform3D();
		tr.setTranslation(new Vector3f(distancia_sol_terra, -2.0f, 0.0f));
		tg = new TransformGroup();
		tg2 = new TransformGroup(tr);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(lua);
		tg2.addChild(tg);
		root.addChild(tg2);
	    alpha = new Alpha(-1, 6000);
	    alpha.setMode(Alpha.INCREASING_ENABLE);
	    alpha.setDecreasingAlphaDuration(6000);
	    rotator = new RotationInterpolator(alpha, tg);
	    rotator.setSchedulingBounds(bounds);
	    rotator.setEnable(true);
	    root.addChild(rotator);
	    System.out.println("Lua à Terra: " + distancia_terra_lua);
	    
	    
	  //Marte
  		Appearance marteApp = createAstro("images/marte.png");
  		martecel = new Celestial("Marte");
  	    Sphere marte = new Sphere(raio_marte, Primitive.GENERATE_TEXTURE_COORDS, 50, marteApp);
	    marte.setName("Marte");
  	    tr = new Transform3D();
		tr.setTranslation(new Vector3f(distancia_sol_marte, 0.0f, 0.0f));
		tg = new TransformGroup();
		tg2 = new TransformGroup(tr);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(marte);
		tg2.addChild(tg);
		root.addChild(tg2);
	    alpha = new Alpha(-1, 6000);
	    alpha.setMode(Alpha.INCREASING_ENABLE);
	    alpha.setDecreasingAlphaDuration(6000);
	    rotator = new RotationInterpolator(alpha, tg);
	    rotator.setSchedulingBounds(bounds);
	    rotator.setEnable(true);
	    root.addChild(rotator);
	    System.out.println("Marte: " + distancia_sol_marte);
	    
	    
	  //Jupiter
  		Appearance jupiterApp = createAstro("images/jupiter.png");
  	    Sphere jupiter = new Sphere(raio_jupiter, Primitive.GENERATE_TEXTURE_COORDS | Primitive.ENABLE_GEOMETRY_PICKING, 50, jupiterApp);
  	    jupiter.setName("Jupiter");
	    tr = new Transform3D();
		tr.setTranslation(new Vector3f(distancia_sol_jupiter, 0.0f, 0.0f));
		tg = new TransformGroup();
		tg2 = new TransformGroup(tr);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(jupiter);
		tg2.addChild(tg);
		root.addChild(tg2);
	    alpha = new Alpha(-1, 6000);
	    alpha.setMode(Alpha.INCREASING_ENABLE);
	    alpha.setDecreasingAlphaDuration(6000);
	    rotator = new RotationInterpolator(alpha, tg);
	    rotator.setSchedulingBounds(bounds);
	    rotator.setEnable(true);
	    root.addChild(rotator);
	    System.out.println("Júpiter: " + distancia_sol_jupiter);
	    
	  //Saturno
	    Appearance saturnoApp = createAstro("images/saturno.jpg");
  		Appearance ringsApp = createAstro("images/saturn_rings.jpg");
		Saturn saturno = new Saturn(saturnoApp, ringsApp, raio_saturno, 20);
		saturno.setName("Saturno");
	    tr = new Transform3D();
		tr.setTranslation(new Vector3f(distancia_sol_saturno, 0.0f, 0.0f));
		tg = new TransformGroup();
		tg2 = new TransformGroup(tr);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(saturno);
		tg2.addChild(tg);
		root.addChild(tg2);
	    alpha = new Alpha(-1, 6000);
	    alpha.setMode(Alpha.INCREASING_ENABLE);
	    alpha.setDecreasingAlphaDuration(6000);
	    rotator = new RotationInterpolator(alpha, tg);
	    rotator.setSchedulingBounds(bounds);
	    rotator.setEnable(true);
	    root.addChild(rotator);
	    System.out.println("Saturno: " + distancia_sol_saturno);
	    
	  //Urano
  		Appearance uranoApp = createAstro("images/urano.jpg");
  	    Sphere urano = new Sphere(raio_urano, Primitive.GENERATE_TEXTURE_COORDS, 50, uranoApp);
	    urano.setName("Urano");
  	    tr = new Transform3D();
		tr.setTranslation(new Vector3f(distancia_sol_urano, 0.0f, 0.0f));
		tg = new TransformGroup();
		tg2 = new TransformGroup(tr);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(urano);
		tg2.addChild(tg);
		root.addChild(tg2);
	    alpha = new Alpha(-1, 6000);
	    alpha.setMode(Alpha.INCREASING_ENABLE);
	    alpha.setDecreasingAlphaDuration(6000);
	    rotator = new RotationInterpolator(alpha, tg);
	    rotator.setSchedulingBounds(bounds);
	    rotator.setEnable(true);
	    root.addChild(rotator);
	    System.out.println("Urano: " + distancia_sol_urano);
	    
	  //Neptuno
  		Appearance neptunoApp = createAstro("images/neptuno.png");
  	    Sphere neptuno = new Sphere(raio_neptuno, Primitive.GENERATE_TEXTURE_COORDS, 50, neptunoApp);
	    neptuno.setName("Neptuno");
  	    tr = new Transform3D();
		tr.setTranslation(new Vector3f(distancia_sol_neptuno, 0.0f, 0.0f));
		tg = new TransformGroup();
		tg2 = new TransformGroup(tr);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(neptuno);
		tg2.addChild(tg);
		root.addChild(tg2);
	    alpha = new Alpha(-1, 6000);
	    alpha.setMode(Alpha.INCREASING_ENABLE);
	    alpha.setDecreasingAlphaDuration(6000);
	    rotator = new RotationInterpolator(alpha, tg);
	    rotator.setSchedulingBounds(bounds);
	    rotator.setEnable(true);
	    root.addChild(rotator);
	    System.out.println("Neptuno: " + distancia_sol_neptuno);
	    
	    
  	    //Raios
	    System.out.println();
	    System.out.println();
	    System.out.println("Raios");
	    System.out.println();
	    System.out.println("Sol: " + raio_sol);
	    System.out.println("Mercúrio: " + raio_mercurio);
	    System.out.println("Venus: " + raio_venus);
	    System.out.println("Terra: " + raio_terra);
	    System.out.println("Marte: " + raio_marte);
	    System.out.println("Júpiter: " + raio_jupiter);
	    System.out.println("Saturno: " + raio_saturno);
	    System.out.println("Urano: " + raio_urano);
	    System.out.println("Neptuno: " + raio_neptuno);
	    
	    
	    //Iluminação	    
	    
	    AmbientLight aLight = new AmbientLight(true, new Color3f(Color.YELLOW));
		aLight.setInfluencingBounds(bounds);
		root.addChild(aLight);
	    
	    dLight = new DirectionalLight(true, new Color3f(Color.WHITE), new Vector3f(0.0f, -4.0f, -4.0f));
	    dLight.setCapability(PointLight.ALLOW_STATE_READ);
		dLight.setCapability(PointLight.ALLOW_STATE_WRITE);
		dLight.setInfluencingBounds(bounds);
		root.addChild(dLight);
		
		
		ColoringAttributes ca;
		Transform3D tr_text;
		
		// Titulo Sistema Solar
		TransformGroup bbTg = new TransformGroup();
		bbTg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		root.addChild(bbTg);

		Billboard bb = new Billboard(bbTg, Billboard.ROTATE_ABOUT_POINT, new Point3f(0f, 0f, 0f));
		bb.setSchedulingBounds(bounds);
		bbTg.addChild(bb);

		tr = new Transform3D();
		tr.rotY(Math.toRadians(25));
		tr.setScale(0.2);
		tr.setTranslation(new Vector3d(-11.5, 8.0, 0.0));
		tg = new TransformGroup(tr);
		
		Font font = new Font("Serif", Font.BOLD, 10);
		FontExtrusion extrusion = new FontExtrusion();
		Font3D font3d = new Font3D(font, extrusion);
		Text3D text = new Text3D(font3d, "Sistema Solar");
		Appearance ap = new Appearance();
		Material ma = new MyMaterial(MyMaterial.GOLD);
		ap.setMaterial(ma);
		Shape3D shape = new Shape3D(text, ap);
		
		tg.addChild(shape);
		bbTg.addChild(tg);
		
		// Titulo Sol
		font = new Font("Serif", Font.BOLD, 6);
		extrusion = new FontExtrusion();
		font3d = new Font3D(font, extrusion);
		text = new Text3D(font3d, "Sol");
		ap = new Appearance();
		ma = new MyMaterial(MyMaterial.PEWTER);
		ap.setMaterial(ma);
		shape = new Shape3D(text, ap);
  	    
  	    tr_text = new Transform3D();
		tr_text.rotY(Math.toRadians(25));
		tr_text.setScale(0.2);
		tr_text.setTranslation(new Vector3d(-0.8, 5.5 , 0.0));
		tg = new TransformGroup();
		tg2 = new TransformGroup(tr_text);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(shape);
		tg2.addChild(tg);
		root.addChild(tg2);
		alpha = new Alpha(-1, 100);
	    alpha.setMode(Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE);
	    alpha.setDecreasingAlphaDuration(1000);
	    translator = new PositionInterpolator(alpha, tg);
        translator.setSchedulingBounds(bounds);
        translator.setEnable(true);
        root.addChild(translator);
		
		// Titulo Mercúrio
		font = new Font("Serif", Font.BOLD, 1);
		extrusion = new FontExtrusion();
		font3d = new Font3D(font, extrusion);
		text = new Text3D(font3d, "Mercúrio");
		ap = new Appearance();
		ma = new MyMaterial(MyMaterial.CHROME);
		ap.setMaterial(ma);
		shape = new Shape3D(text, ap);
		tr_text = new Transform3D();
		tr_text.rotY(Math.toRadians(25));
		tr_text.setScale(0.2);
		tr_text.setTranslation(new Vector3d(distancia_sol - 0.4f, raio_mercurio + 0.2f, 0.0f));
		tg = new TransformGroup();
		tg2 = new TransformGroup(tr_text);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(shape);
		tg2.addChild(tg);
		root.addChild(tg2);
		alpha = new Alpha(-1, 100);
	    alpha.setMode(Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE);
	    alpha.setDecreasingAlphaDuration(1000);
	    translator = new PositionInterpolator(alpha, tg);
        translator.setSchedulingBounds(bounds);
        translator.setEnable(true);
        root.addChild(translator);
		
		// Titulo Venus
		font = new Font("Serif", Font.BOLD, 1);
		extrusion = new FontExtrusion();
		font3d = new Font3D(font, extrusion);
		text = new Text3D(font3d, "Vénus");
		ap = new Appearance();
		ma = new MyMaterial(MyMaterial.BRONZE);
		ap.setMaterial(ma);
		shape = new Shape3D(text, ap);
		tr_text = new Transform3D();
		tr_text.rotY(Math.toRadians(25));
		tr_text.setScale(0.2);
		tr_text.setTranslation(new Vector3d(distancia_sol_venus - 0.3f, raio_venus + 0.2f, 0.0f));
		tg = new TransformGroup();
		tg2 = new TransformGroup(tr_text);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(shape);
		tg2.addChild(tg);
		root.addChild(tg2);
		alpha = new Alpha(-1, 100);
	    alpha.setMode(Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE);
	    alpha.setDecreasingAlphaDuration(1000);
	    translator = new PositionInterpolator(alpha, tg);
        translator.setSchedulingBounds(bounds);
        translator.setEnable(true);
        root.addChild(translator);
		
		// Titulo Terra
		font = new Font("Serif", Font.BOLD, 1);
		extrusion = new FontExtrusion();
		font3d = new Font3D(font, extrusion);
		text = new Text3D(font3d, "Terra");
		ap = new Appearance();
		ca = new ColoringAttributes();
		ca.setColor(new Color3f(Color.GREEN));
		ap.setColoringAttributes(ca);
		shape = new Shape3D(text, ap);
		tr_text = new Transform3D();
		tr_text.rotY(Math.toRadians(25));
		tr_text.setScale(0.2);
		tr_text.setTranslation(new Vector3d(distancia_sol_terra - 0.3f, raio_terra + 0.2f, 0.0f));
		tg = new TransformGroup();
		tg2 = new TransformGroup(tr_text);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(shape);
		tg2.addChild(tg);
		root.addChild(tg2);
		alpha = new Alpha(-1, 100);
	    alpha.setMode(Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE);
	    alpha.setDecreasingAlphaDuration(1000);
	    translator = new PositionInterpolator(alpha, tg);
        translator.setSchedulingBounds(bounds);
        translator.setEnable(true);
        root.addChild(translator);
		
		// Titulo Lua
		font = new Font("Serif", Font.BOLD, 1);
		extrusion = new FontExtrusion();
		font3d = new Font3D(font, extrusion);
		text = new Text3D(font3d, "Lua");
		ap = new Appearance();
		ca = new ColoringAttributes();
		ca.setColor(1.5f, 1.5f, 1.5f);
		ap.setColoringAttributes(ca);
		shape = new Shape3D(text, ap);
		tr_text = new Transform3D();
		tr_text.rotY(Math.toRadians(25));
		tr_text.setScale(0.2);
		tr_text.setTranslation(new Vector3d(distancia_sol_terra - 0.2f, -1.8, -0.4));
		tg = new TransformGroup();
		tg2 = new TransformGroup(tr_text);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(shape);
		tg2.addChild(tg);
		root.addChild(tg2);
		alpha = new Alpha(-1, 100);
	    alpha.setMode(Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE);
	    alpha.setDecreasingAlphaDuration(1000);
	    translator = new PositionInterpolator(alpha, tg);
        translator.setSchedulingBounds(bounds);
        translator.setEnable(true);
        root.addChild(translator);
		
		// Titulo Marte
		font = new Font("Serif", Font.BOLD, 1);
		extrusion = new FontExtrusion();
		font3d = new Font3D(font, extrusion);
		text = new Text3D(font3d, "Marte");
		ap = new Appearance();
		ca = new ColoringAttributes();
		ca.setColor(new Color3f(Color.RED));
		ap.setColoringAttributes(ca);
		shape = new Shape3D(text, ap);
		tr_text = new Transform3D();
		tr_text.rotY(Math.toRadians(25));
		tr_text.setScale(0.2);
		tr_text.setTranslation(new Vector3d(distancia_sol_marte - 0.3f, raio_marte + 0.2f, 0.0f));
		tg = new TransformGroup();
		tg2 = new TransformGroup(tr_text);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(shape);
		tg2.addChild(tg);
		root.addChild(tg2);
		alpha = new Alpha(-1, 100);
	    alpha.setMode(Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE);
	    alpha.setDecreasingAlphaDuration(1000);
	    translator = new PositionInterpolator(alpha, tg);
        translator.setSchedulingBounds(bounds);
        translator.setEnable(true);
        root.addChild(translator);
		
		// Titulo Jupiter
		font = new Font("Serif", Font.BOLD, 1);
		extrusion = new FontExtrusion();
		font3d = new Font3D(font, extrusion);
		text = new Text3D(font3d, "Júpiter");
		ap = new Appearance();
		ma = new MyMaterial(MyMaterial.GOLD);
		ap.setMaterial(ma);
		shape = new Shape3D(text, ap);
		tr_text = new Transform3D();
		tr_text.rotY(Math.toRadians(25));
		tr_text.setScale(0.2);
		tr_text.setTranslation(new Vector3d(distancia_sol_jupiter - 0.2f, raio_jupiter + 0.2f, 0.0f));
		tg = new TransformGroup();
		tg2 = new TransformGroup(tr_text);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(shape);
		tg2.addChild(tg);
		root.addChild(tg2);
		alpha = new Alpha(-1, 100);
	    alpha.setMode(Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE);
	    alpha.setDecreasingAlphaDuration(1000);
	    translator = new PositionInterpolator(alpha, tg);
        translator.setSchedulingBounds(bounds);
        translator.setEnable(true);
        root.addChild(translator);
		
		// Titulo Saturno
		font = new Font("Serif", Font.BOLD, 1);
		extrusion = new FontExtrusion();
		font3d = new Font3D(font, extrusion);
		text = new Text3D(font3d, "Saturno");
		ap = new Appearance();
		ma = new MyMaterial(MyMaterial.PEWTER);
		ap.setMaterial(ma);
		shape = new Shape3D(text, ap);
		tr_text = new Transform3D();
		tr_text.rotY(Math.toRadians(25));
		tr_text.setScale(0.2);
		tr_text.setTranslation(new Vector3d(distancia_sol_saturno - 0.3f, raio_saturno + 0.2f, 0.0f));
		tg = new TransformGroup();
		tg2 = new TransformGroup(tr_text);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(shape);
		tg2.addChild(tg);
		root.addChild(tg2);
		alpha = new Alpha(-1, 100);
	    alpha.setMode(Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE);
	    alpha.setDecreasingAlphaDuration(1000);
	    translator = new PositionInterpolator(alpha, tg);
        translator.setSchedulingBounds(bounds);
        translator.setEnable(true);
        root.addChild(translator);
		
		// Titulo Urano
		font = new Font("Serif", Font.BOLD, 1);
		extrusion = new FontExtrusion();
		font3d = new Font3D(font, extrusion);
		text = new Text3D(font3d, "Urâno");
		ap = new Appearance();
		ca = new ColoringAttributes();
		ca.setColor(new Color3f(Color.CYAN));
		ap.setColoringAttributes(ca);
		shape = new Shape3D(text, ap);
		tr_text = new Transform3D();
		tr_text.rotY(Math.toRadians(25));
		tr_text.setScale(0.2);
		tr_text.setTranslation(new Vector3d(distancia_sol_urano - 0.3f, raio_urano + 0.2f, 0.0f));
		tg = new TransformGroup();
		tg2 = new TransformGroup(tr_text);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(shape);
		tg2.addChild(tg);
		root.addChild(tg2);
		alpha = new Alpha(-1, 100);
	    alpha.setMode(Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE);
	    alpha.setDecreasingAlphaDuration(1000);
	    translator = new PositionInterpolator(alpha, tg);
        translator.setSchedulingBounds(bounds);
        translator.setEnable(true);
        root.addChild(translator);
		
		// Titulo Neptuno
		font = new Font("Serif", Font.BOLD, 1);
		extrusion = new FontExtrusion();
		font3d = new Font3D(font, extrusion);
		text = new Text3D(font3d, "Neptuno");
		ap = new Appearance();
		ca = new ColoringAttributes();
		ca.setColor(new Color3f(Color.BLUE));
		ap.setColoringAttributes(ca);
		shape = new Shape3D(text, ap);
		tr_text = new Transform3D();
		tr_text.rotY(Math.toRadians(25));
		tr_text.setScale(0.2);
		tr_text.setTranslation(new Vector3d(distancia_sol_neptuno - 0.35f, raio_neptuno + 0.2f, 0.0f));
		tg = new TransformGroup();
		tg2 = new TransformGroup(tr_text);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(shape);
		tg2.addChild(tg);
		root.addChild(tg2);
		alpha = new Alpha(-1, 100);
	    alpha.setMode(Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE);
	    alpha.setDecreasingAlphaDuration(1000);
	    translator = new PositionInterpolator(alpha, tg);
        translator.setSchedulingBounds(bounds);
        translator.setEnable(true);
        root.addChild(translator);
        
     // Titulo Autor
		bbTg = new TransformGroup();
		bbTg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		root.addChild(bbTg);

		bb = new Billboard(bbTg, Billboard.ROTATE_ABOUT_POINT, new Point3f(0f, 0f, 0f));
		bb.setSchedulingBounds(bounds);
		bbTg.addChild(bb);

		tr = new Transform3D();
		tr.rotY(Math.toRadians(-25));
		tr.setScale(0.1);
		tr.setTranslation(new Vector3d(distancia_sol_terra + 2.0, 4.0, 0.0));
		tg = new TransformGroup(tr);
		font = new Font("Serif", Font.BOLD, 10);
		extrusion = new FontExtrusion();
		font3d = new Font3D(font, extrusion);
		text = new Text3D(font3d, "Vasco Ventura");
		ap = new Appearance();
		ca = new ColoringAttributes();
		ca.setColor(new Color3f(Color.YELLOW));
		ap.setColoringAttributes(ca);
		shape = new Shape3D(text, ap);
		
		tg.addChild(shape);
		bbTg.addChild(tg);
		
		
		
		// Background
		Background background = createBackground();
		background.setApplicationBounds(bounds);
		root.addChild(background);
		
		
		//Spaceship
		Appearance topApp = new Appearance();
		topApp.setMaterial(new MyMaterial(MyMaterial.BRONZE));
		Appearance bottomApp = new Appearance();
		bottomApp.setMaterial(new MyMaterial(MyMaterial.PEWTER));
		Appearance button1 = new Appearance();
		ca = new ColoringAttributes();
		ca.setColor(new Color3f(Color.RED));
		button1.setColoringAttributes(ca);
		Appearance button2 = new Appearance();
		ca = new ColoringAttributes();
		ca.setColor(new Color3f(Color.GREEN));
		button2.setColoringAttributes(ca);
		Appearance farolapp = new Appearance();
		ca = new ColoringAttributes();
		ca.setColor(new Color3f(Color.YELLOW));
		farolapp.setColoringAttributes(ca);
		Spaceship spaceship = new Spaceship(topApp, bottomApp, button1, button2, farolapp);
		tr = new Transform3D();
		tr.setTranslation(new Vector3d(0.1, 7.5,0.0));
		tg = new TransformGroup(tr);
		tg.addChild(spaceship);
		TransformGroup spinSec = new TransformGroup();
	    spinSec.addChild(tg);
	    spinSec.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    root.addChild(spinSec);	   
	    MoveControl rotator = new MoveControl(spinSec, 5000, 200);
	    rotator.setSchedulingBounds(bounds);
	    root.addChild(rotator);
	    
	  //Diamond
  		Appearance diaApp = new Appearance();
  		diaApp.setPolygonAttributes(new PolygonAttributes(
		PolygonAttributes.POLYGON_LINE,
		PolygonAttributes.CULL_BACK, 0));
  		ca = new ColoringAttributes();
  		ca.setColor(new Color3f(2.12f, 0.19f, 1.41f));
  		diaApp.setColoringAttributes(ca);	
  		Diamond diamond = new Diamond(diaApp);
  		tr.setScale(3.0);
  		tr.setTranslation(new Vector3d(-8.0, 2.5, 0.0));
  		tg = new TransformGroup(tr);
  	    tg.addChild(diamond);
  	    tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
  	    root.addChild(tg);	   
	  	    
	    
		
		return root;
	}
	
	Appearance createAstro(String path){
	    Appearance appear = new Appearance();
	    URL filename =
	    getClass().getClassLoader().getResource(path);
	    TextureLoader loader = new TextureLoader(filename, this);
	    Texture texture = loader.getTexture();
	    appear.setTexture(texture);
	    return appear;
	  }
	  
	  Background createBackground(){
	    Background background = new Background();
	    BranchGroup bg = new BranchGroup();
	    Sphere sphere = new Sphere(1.0f, Sphere.GENERATE_NORMALS |
	    Sphere.GENERATE_NORMALS_INWARD |
	    Sphere.GENERATE_TEXTURE_COORDS, 60);
	    Appearance ap = sphere.getAppearance();
	    bg.addChild(sphere);
	    background.setGeometry(bg);
	    
	    URL filename =
	    getClass().getClassLoader().getResource("images/sky2.jpg");
	    TextureLoader loader = new TextureLoader(filename, this);
	    Texture texture = loader.getTexture();
	    ap.setTexture(texture);
	    return background;
	  }

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		pc.setShapeLocation(mouseEvent);
	    PickResult result = pc.pickClosest();
	 		
	    Sphere nodeS = (Sphere) result.getNode(PickResult.GROUP);
	    Shape3D nodeG = (Shape3D) result.getNode(PickResult.SHAPE3D);
		if (nodeS != null) {			
				if(nodeS.toString().contains("Sol")) {
					ta.setText("Nome: " + solcel.getNome() + "\n\n");
					ta.append("Massa: " + solcel.getMassa() + "\n\n");
					ta.append("Descrição: " + solcel.getDescricao() + "\n\n");
					ta.append("Raio: " + solcel.getRaio() + " km\n\n" );
					
					boolean vLight = dLight.getEnable();
					if(vLight) {
						dLight.setEnable(false);
					} else {
						dLight.setEnable(true);
					}
					
				} else if(nodeS.toString().contains("Mercurio")) {
					ta.setText("Nome: " + mercuriocel.getNome() + "\n\n");
					ta.append("Massa: " + mercuriocel.getMassa() + "\n\n");
					ta.append("Descrição: " + mercuriocel.getDescricao() + "\n\n");
					ta.append("Raio: " + mercuriocel.getRaio() + " km\n\n" );
					ta.append("Período Orbital: " + mercuriocel.getPeriodo_orbital() + " dias\n\n" );
				} else if(nodeS.toString().contains("Venus")) {
					ta.setText("Nome: " + venuscel.getNome() + "\n\n");
					ta.append("Massa: " + venuscel.getMassa() + "\n\n");
					ta.append("Descrição: " + venuscel.getDescricao() + "\n\n");
					ta.append("Raio: " + venuscel.getRaio() + " km\n\n" );
					ta.append("Período Orbital: " + venuscel.getPeriodo_orbital() + " dias\n\n" );
				} else if(nodeS.toString().contains("Terra")) {
					ta.setText("Nome: " + terracel.getNome() + "\n\n");
					ta.append("Massa: " + terracel.getMassa() + "\n\n");
					ta.append("Descrição: " + terracel.getDescricao() + "\n\n");
					ta.append("Raio: " + terracel.getRaio() + " km\n\n" );
					ta.append("Período Orbital: " + terracel.getPeriodo_orbital() + " dias\n\n" );
				} else if(nodeS.toString().contains("Lua")) {
					ta.setText("Nome: " + luacel.getNome() + "\n\n");
					ta.append("Massa: " + luacel.getMassa() + "\n\n");
					ta.append("Descrição: " + luacel.getDescricao() + "\n\n");
					ta.append("Raio: " + luacel.getRaio() + " km\n\n" );
					ta.append("Período Orbital à Terra: " + luacel.getPeriodo_orbital() + " dias\n\n" );
				} else if(nodeS.toString().contains("Marte")) {
					ta.setText("Nome: " + martecel.getNome() + "\n\n");
					ta.append("Massa: " + martecel.getMassa() + "\n\n");
					ta.append("Descrição: " + martecel.getDescricao() + "\n\n");
					ta.append("Raio: " + martecel.getRaio() + " km\n\n" );
					ta.append("Período Orbital: " + martecel.getPeriodo_orbital() + " dias\n\n" );
				} else if(nodeS.toString().contains("Jupiter")) {
					ta.setText("Nome: " + jupitercel.getNome() + "\n\n");
					ta.append("Massa: " + jupitercel.getMassa() + "\n\n");
					ta.append("Descrição: " + jupitercel.getDescricao() + "\n\n");
					ta.append("Raio: " + jupitercel.getRaio() + " km\n\n" );
					ta.append("Período Orbital: " + jupitercel.getPeriodo_orbital() + " dias\n\n" );
				} else if(nodeS.toString().contains("Urano")) {
					ta.setText("Nome: " + uranocel.getNome() + "\n\n");
					ta.append("Massa: " + uranocel.getMassa() + "\n\n");
					ta.append("Descrição: " + uranocel.getDescricao() + "\n\n");
					ta.append("Raio: " + uranocel.getRaio() + " km\n\n" );
					ta.append("Período Orbital: " + uranocel.getPeriodo_orbital() + " dias\n\n" );
				} else if(nodeS.toString().contains("Neptuno")) {
					ta.setText("Nome: " + neptunocel.getNome() + "\n\n");
					ta.append("Massa: " + neptunocel.getMassa() + "\n\n");
					ta.append("Descrição: " + neptunocel.getDescricao() + "\n\n");
					ta.append("Raio: " + neptunocel.getRaio() + " km\n\n" );
					ta.append("Período Orbital: " + neptunocel.getPeriodo_orbital() + " dias\n\n" );
		    	}
		} else if(nodeG != null) {
			if((nodeG.toString().contains("Rings")) || (nodeS.toString().contains("Saturno"))) {
				ta.setText("Nome: " + saturnocel.getNome() + "\n");
				ta.append("Massa: " + saturnocel.getMassa() + "\n");
				ta.append("Descrição: " + saturnocel.getDescricao() + "\n");
				ta.append("Raio: " + saturnocel.getRaio() + " km\n\n" );
				ta.append("Período Orbital: " + saturnocel.getPeriodo_orbital() + " dias\n\n" );
			}
		} else {
			System.out.println("Nenhum Objeto Selecionado!");
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			
			enable = true;
			
			if(enable)  {				
					char key = e.getKeyChar();
					if(key == 'd' || key =='D') {
						i++;
						System.out.println(i);
						System.out.println(pontos_camera[0][i]);
						System.out.println(pontos_camera[1][i]);
						viewTr = new Transform3D();
						viewTr.lookAt(pontos_camera[0][i], pontos_camera[1][i], new Vector3d(0,1,0));
						viewTr.invert();
						su.getViewingPlatform().getViewPlatformTransform().setTransform(viewTr);
						bSound.setEnable(true);
						if(pontos_camera[0][i] == null || pontos_camera[1][i] == null) {
							i = 0;
						}
						
				} else if(key == 'a' || key =='A') {
						i--;
						System.out.println(i);
						System.out.println(pontos_camera[0][i]);
						System.out.println(pontos_camera[1][i]);
						viewTr = new Transform3D();
						viewTr.lookAt(pontos_camera[0][i], pontos_camera[1][i], new Vector3d(0,1,0));
						viewTr.invert();
						su.getViewingPlatform().getViewPlatformTransform().setTransform(viewTr);
						bSound.setEnable(true);
				}	
			}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
String cmd = e.getActionCommand();
		
		if("Capture Image".equals(cmd)) {
			BufferedImage bi = capture();
			
			save(bi);
			
			
		} else if("Exit".equals(cmd)) {
			System.exit(0);
			
		}
		
	}
	
	public BufferedImage capture() {
	    // render off screen image
	    Dimension dim = cv.getSize();
	    view.stopView();
	    view.addCanvas3D(offScreenCanvas);
	    BufferedImage bImage =
	    new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
	    ImageComponent2D buffer =
	    new ImageComponent2D(ImageComponent.FORMAT_RGB, bImage);
	    offScreenCanvas.setOffScreenBuffer(buffer);
	    view.startView();
	    offScreenCanvas.renderOffScreenBuffer();
	    offScreenCanvas.waitForOffScreenRendering();
	    bImage = offScreenCanvas.getOffScreenBuffer().getImage();
	    view.removeCanvas3D(offScreenCanvas);
	    return bImage;
	  }
	  
	  public void save(BufferedImage bImage) {
	    // save image to file
	    JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new File("."));
	    if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
	      File oFile = chooser.getSelectedFile();
	      try {
	        ImageIO.write(bImage, "jpeg", oFile);
	      } catch (IOException ex) {
	        ex.printStackTrace();
	      }
	    }
	  }
}
