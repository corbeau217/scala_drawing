package Main

import java.awt.Graphics
import java.awt.Color


// ################################################################################
// ################################################################################

class CelestialMass(_name:String,_radius:Double,_soi:Double,_synchronous:Double, _bodyColor:Color) {
  val name = _name
  val bodyRadius = ConstLib.scaleFactor * _radius
  val soiRadius = ConstLib.scaleFactor * _soi
  val synchRadius = ConstLib.scaleFactor * _synchronous

  val bodyDiam = 2 * bodyRadius
  val soiDiam = 2 * soiRadius
  val synchDiam = 2 * synchRadius

  val bodyColor = new Color(_bodyColor.getRGB())
  val soiColor = new Color(bodyColor.getRed(),bodyColor.getGreen(),bodyColor.getBlue(),(255/10))
  

  var centerX = 0.0
  var centerY = 0.0


  def paint(g:Graphics,_x:Double,_y:Double):Unit={
    // update the position
    centerX = _x
    centerY = _y

    // soi

    val soiX = _x - soiRadius
    val soiY = _y - soiRadius
    
    g.setColor(soiColor)
    g.fillOval(soiX.toInt,soiY.toInt,soiDiam.toInt,soiDiam.toInt)

    // body

    val bodyX = _x - bodyRadius
    val bodyY = _y - bodyRadius
    
    g.setColor(bodyColor)
    g.fillOval(bodyX.toInt,bodyY.toInt,bodyDiam.toInt,bodyDiam.toInt)

    // synch orbit

    val synchX = _x-synchRadius
    val synchY = _y-synchRadius


    g.setColor(Color.WHITE)
    g.drawLine(_x.toInt,_y.toInt,(_x+synchRadius).toInt,_y.toInt)
    g.drawOval(synchX.toInt,synchY.toInt,synchDiam.toInt,synchDiam.toInt)
  }

}

// ################################################################################
// ################################################################################

class Orbit(_orbitee:Option[CelestialMass],_orbiter:Option[CelestialMass],_apoapsis:Double,_periapsis:Double, _orbitColor:Color) {
  // the reference point for this orbit/root of the orbit
  val orbitee = _orbitee
  // what is orbiting around the orbitee
  val orbiter = _orbiter

  // for now use only apoapsis for perfect circle, but later use trig and inclination + longitude ascending node

  // height (idk use -1 for max height)
  val apoapsis = _apoapsis
  // depth (minimum 0)
  val periapsis = _periapsis

  val orbitColor = new Color(_orbitColor.getRGB())
  val radiusColor = new Color(orbitColor.getRed(),orbitColor.getGreen(),orbitColor.getBlue(),(255/5))

  def paint(g:Graphics,_screenCenterX:Double,_screenCenterY:Double):Unit={
    // initialise them as 0.0 for later update
    var orbitCenterX = 0.0
    var orbitCenterY = 0.0

    // put the offsets here when we do trig for inclination (before get center of mass position)
    

    // try add position of the thing they're orbiting
    orbitee match {
      case None => { // was nothing
        orbitCenterX = _screenCenterX
        orbitCenterY = _screenCenterY
      }
      case Some(body) => { // has something
        orbitCenterX = body.centerX
        orbitCenterY = body.centerY
      }
    }

    // draw apoapsis height line
    g.setColor(radiusColor)
    g.drawLine(orbitCenterX.toInt,orbitCenterY.toInt,(orbitCenterX+apoapsis).toInt,orbitCenterY.toInt)

    // draw orbit
    g.setColor(orbitColor)
    g.drawOval(orbitCenterX.toInt,orbitCenterY.toInt,apoapsis.toInt,apoapsis.toInt)

    orbiter match {
      case None => { /* honk shoo */}
      case Some(body) => {
        // hand off for their paintabling
        body.paint(g,_screenCenterX,_screenCenterY)
      }
    }
  }

}

// ################################################################################
// ################################################################################



// ################################################################################
// ################################################################################
