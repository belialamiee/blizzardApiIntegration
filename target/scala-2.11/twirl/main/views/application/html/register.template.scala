
package views.application.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object register_Scope0 {
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

class register extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(error: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.17*/("""
"""),_display_(/*2.2*/main(title = "Home")/*2.22*/ {_display_(Seq[Any](format.raw/*2.24*/("""

"""),format.raw/*4.1*/("""<!DOCTYPE html>
    <html>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Register</h3>
            </div>
            <div class="panel-body">
                <form action="/register" method="post" class="form-horizontal">
                    <div class="form-group">
                        <div class="row">
                            <label class="col-xs-2 col-xs-offset-2" for="email">
                                Email Address
                            </label>
                            <div class="col-xs-4">
                                <input type="email" class="form-control"  name="email" id="email">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <label for="password" class="col-xs-2 col-xs-offset-2">
                                Password
                            </label>
                            <div class="col-xs-4">
                                <input type="password" class="form-control" name="password" id="password">

                            </div>
                        </div>
                    </div>
                    """),_display_(/*33.22*/if(error != null)/*33.39*/ {_display_(Seq[Any](format.raw/*33.41*/("""
                        """),format.raw/*34.25*/("""<div>"""),_display_(/*34.31*/error),format.raw/*34.36*/("""</div>
                    """)))}),format.raw/*35.22*/("""
                    """),format.raw/*36.21*/("""<button class="btn btn-default" type="submit">Register</button>
                </form>
            </div>
        </div>
    </html>
""")))}))
      }
    }
  }

  def render(error:String): play.twirl.api.HtmlFormat.Appendable = apply(error)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (error) => apply(error)

  def ref: this.type = this

}


}

/**/
object register extends register_Scope0.register
              /*
                  -- GENERATED --
                  DATE: Tue Sep 06 22:12:44 AEST 2016
                  SOURCE: /var/www/uni/overwatch/app/views.application/register.scala.html
                  HASH: 01032c2b57505c642eadfe38b0eff93bdb98fbeb
                  MATRIX: 763->1|873->16|900->18|928->38|967->40|995->42|2337->1357|2363->1374|2403->1376|2456->1401|2489->1407|2515->1412|2574->1440|2623->1461
                  LINES: 27->1|32->1|33->2|33->2|33->2|35->4|64->33|64->33|64->33|65->34|65->34|65->34|66->35|67->36
                  -- GENERATED --
              */
          