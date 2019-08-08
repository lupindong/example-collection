package example.core

import gigahorse.support.okhttp.Gigahorse
import play.api.libs.json._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._

object Weather {
  lazy val http = Gigahorse.http(Gigahorse.config)

  def weather: Future[String] = {
    val baseUrl = "https://www.metaweather.com/api/location"
    val locUrl = baseUrl + "/search/"
    val weatherUrl = baseUrl + "/%s/"
    val rLoc = Gigahorse.url(locUrl).get
      .addQueryString("query" -> "New York")

    for {
      loc <- http.run(rLoc, parse)
      woeid = (loc \ 0 \ "woeid").get
      rWeather = Gigahorse.url(weatherUrl format woeid).get
      weather <- http.run(rWeather, parse)
    } yield (weather \\ "weather_state_name") (0).as[String].toLowerCase
  }

  private def parse = Gigahorse.asString andThen Json.parse
}