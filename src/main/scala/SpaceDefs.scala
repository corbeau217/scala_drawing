package Main

import java.awt.Graphics
import java.awt.Color


// ################################################################################
// ################################################################################

class CelestialBody(_radius:Double,_soi:Double,_synchronous:Double, _bodyColor:Color) {
  
  val bodyRadius = ConstLib.scaleFactor * _radius
  val soiRadius = ConstLib.scaleFactor * _soi
  val synchRadius = ConstLib.scaleFactor * _synchronous

  val bodyDiam = 2 * bodyRadius
  val soiDiam = 2 * soiRadius
  val synchDiam = 2 * synchRadius

  val bodyColor = new Color(_bodyColor.getRGB())
  val soiColor = new Color(bodyColor.getRed(),bodyColor.getGreen(),bodyColor.getBlue(),(255/10))
  


  def paint(g:Graphics,_x:Double,_y:Double):Unit={
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



// ################################################################################
// ################################################################################
