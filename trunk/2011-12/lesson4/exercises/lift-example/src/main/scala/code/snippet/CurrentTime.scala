package code
package snippet

import _root_.scala.xml.{NodeSeq, Text}
import _root_.scala.xml.{NodeSeq, Text}
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import _root_.java.util.Date
import code.lib._
import Helpers._

object WhatTime {
  def render = "#current_time" #> (new Date).toString
}