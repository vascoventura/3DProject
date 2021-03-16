package java3dproject;

import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.Node;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupAnd;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnCollisionEntry;
import javax.media.j3d.WakeupOnElapsedFrames;
import javax.media.j3d.WakeupOnElapsedTime;
import javax.media.j3d.WakeupOrOfAnds;
import javax.vecmath.Vector3d;


public class MoveControl extends Behavior{
	
	int i=1;
	double j;	
	TransformGroup translate;
	long tempoInicio;
	long tempoCiclo;
	
	
	
	public MoveControl(TransformGroup translation, long starttime, long looptime) {
		translate = translation;
		tempoInicio = starttime;
		tempoCiclo = looptime;
	}

	@Override
	public void initialize() {				
		
		wakeupOn(new WakeupOnElapsedTime(tempoInicio));		
	}

	@Override
	public void processStimulus(Enumeration criteria) {
		j = j + 0.015;
		i++;
		Transform3D tr = new Transform3D();
	    tr.setTranslation(new Vector3d(0.1 * i, 0 - j,0.0));
	    translate.setTransform(tr);	   
		wakeupOn(new WakeupOnElapsedTime(tempoCiclo));	
		//}
	}
	
}
