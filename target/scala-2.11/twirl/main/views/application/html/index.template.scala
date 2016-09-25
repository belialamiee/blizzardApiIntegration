
package views.application.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object index_Scope0 {
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

class index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](_display_(/*1.2*/main(title = "Character Display")/*1.35*/ {_display_(Seq[Any](format.raw/*1.37*/("""
    """),format.raw/*2.5*/("""<style>
    .item """),format.raw/*3.11*/("""{"""),format.raw/*3.12*/("""
        """),format.raw/*4.9*/("""height: 56px;
        margin: 5px;
        horiz-align: center;
    """),format.raw/*7.5*/("""}"""),format.raw/*7.6*/("""

    """),format.raw/*9.5*/("""</style>
    <link rel="stylesheet" href="http://cdn.jsdelivr.net/bootstrap/3.2.0/css/bootstrap.css" />
   <div class="container">
     Please log in
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
object index extends index_Scope0.index
              /*
                  -- GENERATED --
                  DATE: Mon Sep 12 17:57:02 AEST 2016
                  SOURCE: /var/www/uni/overwatch/app/views.application/index.scala.html
                  HASH: c133425930bd8085e7e460cabd0e78ed8bc91c0f
                  MATRIX: 839->1|880->34|919->36|950->41|995->59|1023->60|1058->69|1152->137|1179->138|1211->144
                  LINES: 32->1|32->1|32->1|33->2|34->3|34->3|35->4|38->7|38->7|40->9
                  -- GENERATED --
              */
          