
package views.application.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object login_Scope0 {
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

class login extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(error: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.17*/("""
"""),_display_(/*2.2*/main(title = "Home")/*2.22*/ {_display_(Seq[Any](format.raw/*2.24*/("""
"""),format.raw/*3.1*/("""<!DOCTYPE html>
    <html>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Login</h3>
            </div>
            <div class="panel-body">
                <form action="/login" method="post" class="form-horizontal">
                    <div class="form-group">
                        <div class="row">
                            <label class="col-xs-2 col-xs-offset-2" for="email">Email Address</label>
                            <div class="col-xs-4">
                                <input type="email" class="form-control" name="email" id="email">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <label for="password" class="col-xs-2 col-xs-offset-2">
                                Password
                            </label>
                            <div class="col-xs-4 ">
                                <input type="password"  class="form-control" name="password" id="password">
                            </div>
                        </div>
                    </div>

                    """),_display_(/*30.22*/if(error != null)/*30.39*/ {_display_(Seq[Any](format.raw/*30.41*/("""
                        """),format.raw/*31.25*/("""<div>"""),_display_(/*31.31*/error),format.raw/*31.36*/("""</div>
                    """)))}),format.raw/*32.22*/("""
                    """),format.raw/*33.21*/("""<button class="btn btn-default" type="submit">Log In</button>
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
object login extends login_Scope0.login
              /*
                  -- GENERATED --
                  DATE: Tue Sep 06 22:12:44 AEST 2016
                  SOURCE: /var/www/uni/overwatch/app/views.application/login.scala.html
                  HASH: e91595cfa6c18942dd1f08d32f64bb9cee405020
                  MATRIX: 757->1|867->16|894->18|922->38|961->40|988->41|2263->1289|2289->1306|2329->1308|2382->1333|2415->1339|2441->1344|2500->1372|2549->1393
                  LINES: 27->1|32->1|33->2|33->2|33->2|34->3|61->30|61->30|61->30|62->31|62->31|62->31|63->32|64->33
                  -- GENERATED --
              */
          