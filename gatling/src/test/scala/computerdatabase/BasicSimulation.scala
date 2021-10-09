package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8085/apimicros/microservicio") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val scn = scenario("Consulta usuarios de negocio") // A scenario is a chain of requests and pauses
    .exec(http("Consulta Usuarios")
      .get("/users"))
    .pause(7)
    .exec(http("Validar ping")
      .get("/ping"))
    .pause(7) // Note that Gatling has recorded real time pauses
     

  setUp(
    scn.inject(
      rampUsers(10000).during(5.seconds),
  ).protocols(httpProtocol))
}
