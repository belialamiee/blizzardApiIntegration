
package views.application.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object characters_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._

class characters extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](_display_(/*1.2*/main(title = "Character Display")/*1.35*/{_display_(Seq[Any](format.raw/*1.36*/("""
    """),format.raw/*2.5*/("""<style>
    .item """),format.raw/*3.11*/("""{"""),format.raw/*3.12*/("""
        """),format.raw/*4.9*/("""height: 56px;
        margin: 5px;
        horiz-align: center;
    """),format.raw/*7.5*/("""}"""),format.raw/*7.6*/("""
    """),format.raw/*8.5*/("""</style>
    <link rel="stylesheet" href="http://cdn.jsdelivr.net/bootstrap/3.2.0/css/bootstrap.css" />
    <script src="http://cdn.jsdelivr.net/angularjs/1.2.20/angular.js"></script>
    <script src="/assets/characters.js" type="text/javascript"></script>
    <div class="container">
        <h1>Character Display</h1>
        <div ng-app="characterData">
            <character-fetcher></character-fetcher>
        </div>
    </div>
""")))}))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


}

/**/
object characters extends characters_Scope0.characters
              /*
                  -- GENERATED --
                  DATE: Tue Sep 13 21:43:39 AEST 2016
                  SOURCE: /var/www/uni/overwatch/app/views.application/characters.scala.html
                  HASH: 7d5602a804d7e3531904247ab71c0dc76195f37b
                  MATRIX: 849->1|890->34|928->35|959->40|1004->58|1032->59|1067->68|1161->136|1188->137|1219->142
                  LINES: 32->1|32->1|32->1|33->2|34->3|34->3|35->4|38->7|38->7|39->8
                  -- GENERATED --
              */
          