package java3dproject;


import javax.media.j3d.Appearance;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Group;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.geometry.Sphere;

import appearance.MyMaterial;


public class Spaceship extends Group {
	
	public Spaceship(Appearance topApp, Appearance bottomApp, Appearance button1app, Appearance button2app, Appearance farolapp) {
		
		//Top
		Sphere top = new Sphere(1.0f, topApp);
		Transform3D tr = new Transform3D();
		tr.setScale(0.5);		
		TransformGroup tg = new TransformGroup(tr);
		tg.addChild(top);
		this.addChild(tg);
		
		//Bottom
		Bottom bottom = new Bottom(bottomApp);
		tr = new Transform3D();
		tr.setScale(0.5);
		tr.setTranslation(new Vector3d(0,-0.5,0));
		tg = new TransformGroup(tr);
		tg.addChild(bottom);
		this.addChild(tg);
		
		//Button2
		Sphere button1 = new Sphere(0.3f, button1app);
		tr = new Transform3D();
		tr.setScale(0.2);
		tr.setTranslation(new Vector3d(0,-0.25,0.75));
		tg = new TransformGroup(tr);
		tg.addChild(button1);
		this.addChild(tg);
		
		//Button2
		Sphere button2 = new Sphere(0.3f, button2app);
		tr = new Transform3D();
		tr.setScale(0.2);
		tr.setTranslation(new Vector3d(0.2,-0.25,0.75));
		tg = new TransformGroup(tr);
		tg.addChild(button2);
		this.addChild(tg);
		
		//Farol1
		Cone cone1 = new Cone(0.2f, 0.4f, farolapp);
		tr = new Transform3D();
		tr.rotZ(Math.toRadians(90));
		tr.setTranslation(new Vector3d(0.75,-0.25,0.60));
		tg = new TransformGroup(tr);
		tg.addChild(cone1);
		this.addChild(tg);
		
		//Farol1
		Cone cone2 = new Cone(0.2f, 0.4f, farolapp);
		tr = new Transform3D();
		tr.rotZ(Math.toRadians(90));
		tr.setTranslation(new Vector3d(0.75,-0.25,-0.60));
		tg = new TransformGroup(tr);
		tg.addChild(cone2);
		this.addChild(tg);
		
	}

}

class Bottom extends Shape3D{
	public Bottom(Appearance app) {
		
		QuadArray qa = new QuadArray(24, GeometryArray.COORDINATES);
		Point3f[] cordenadas = new Point3f[24];
		cordenadas[0] = new Point3f(-2,0,2);
		cordenadas[1] = new Point3f(2,0,2);
		cordenadas[2] = new Point3f(2,0,-2);
		cordenadas[3] = new Point3f(-2,0,-2);
		
		cordenadas[4] = new Point3f(-2,0,2);
		cordenadas[5] = new Point3f(2,0,2);
		cordenadas[6] = new Point3f(1,1,1);
		cordenadas[7] = new Point3f(-1,1,1);
		
		cordenadas[8] = new Point3f(1,1,1);
		cordenadas[9] = new Point3f(2,0,2);
		cordenadas[10] = new Point3f(2,0,-2);
		cordenadas[11] = new Point3f(1,1,-1);
		
		cordenadas[12] = new Point3f(-2,0,-2);
		cordenadas[13] = new Point3f(-1,1,-1);
		cordenadas[14] = new Point3f(1,1,-1);
		cordenadas[15] = new Point3f(2,0,-2);
		
		cordenadas[16] = new Point3f(-2,0,2);
		cordenadas[17] = new Point3f(-1,1,1);
		cordenadas[18] = new Point3f(-1,1,-1);
		cordenadas[19] = new Point3f(-2,0,-2);
		
		cordenadas[20] = new Point3f(-1,2,1);
		cordenadas[21] = new Point3f(1,2,1);
		cordenadas[22] = new Point3f(1,2,-1);
		cordenadas[23] = new Point3f(-1,2,-1);
		
		qa.setCoordinates(0, cordenadas);
		
		GeometryInfo gi = new GeometryInfo(qa);
		NormalGenerator ng = new NormalGenerator();
		ng.generateNormals(gi);

		this.setGeometry(gi.getGeometryArray());
		
		this.setAppearance(app);
		
		
	}
	
}


