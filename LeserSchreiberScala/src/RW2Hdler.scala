import scala.actors.Actor
import scala.actors.Actor._

case object STARTWRITE
case object ENDWRITE
case object STARTREAD
case object ENDREAD
case object REGWRITER

class RW2Hdler extends Actor {
  def act() {
    loop {
      react {
        case STARTREAD => {
          
        }
        case ENDREAD => {
          
        }
        case STARTWRITE => {
          
        }
        case REGWRITER => {
          
        }
        case ENDWRITE => {
          
        }
        case _ => {
          
        }
        
	  }
	}
  }
}
