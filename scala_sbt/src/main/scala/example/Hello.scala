package example

import example.core.Weather

import scala.concurrent._
import scala.concurrent.duration._

object Hello extends App {
  val w = Await.result(Weather.weather, 20.seconds)
  println(s"Hello! The weather in New York is $w.")
  Weather.http.close()
}