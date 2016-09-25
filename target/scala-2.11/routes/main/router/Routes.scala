
// @GENERATOR:play-routes-compiler
// @SOURCE:/var/www/uni/overwatch/conf/routes
// @DATE:Mon Sep 12 17:43:39 AEST 2016

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:5
  Application_2: controllers.Application,
  // @LINE:7
  UserController_0: controllers.UserController,
  // @LINE:18
  Assets_1: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:5
    Application_2: controllers.Application,
    // @LINE:7
    UserController_0: controllers.UserController,
    // @LINE:18
    Assets_1: controllers.Assets
  ) = this(errorHandler, Application_2, UserController_0, Assets_1, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, Application_2, UserController_0, Assets_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.Application.index()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """characters""", """controllers.UserController.characters()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """login""", """controllers.UserController.loginForm()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """register""", """controllers.UserController.registerForm()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """login""", """controllers.UserController.doLogin()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """register""", """controllers.UserController.doRegister()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:5
  private[this] lazy val controllers_Application_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_Application_index0_invoker = createInvoker(
    Application_2.index(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "index",
      Nil,
      "GET",
      """""",
      this.prefix + """"""
    )
  )

  // @LINE:7
  private[this] lazy val controllers_UserController_characters1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("characters")))
  )
  private[this] lazy val controllers_UserController_characters1_invoker = createInvoker(
    UserController_0.characters(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "characters",
      Nil,
      "GET",
      """""",
      this.prefix + """characters"""
    )
  )

  // @LINE:9
  private[this] lazy val controllers_UserController_loginForm2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("login")))
  )
  private[this] lazy val controllers_UserController_loginForm2_invoker = createInvoker(
    UserController_0.loginForm(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "loginForm",
      Nil,
      "GET",
      """""",
      this.prefix + """login"""
    )
  )

  // @LINE:10
  private[this] lazy val controllers_UserController_registerForm3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("register")))
  )
  private[this] lazy val controllers_UserController_registerForm3_invoker = createInvoker(
    UserController_0.registerForm(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "registerForm",
      Nil,
      "GET",
      """""",
      this.prefix + """register"""
    )
  )

  // @LINE:11
  private[this] lazy val controllers_UserController_doLogin4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("login")))
  )
  private[this] lazy val controllers_UserController_doLogin4_invoker = createInvoker(
    UserController_0.doLogin(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "doLogin",
      Nil,
      "POST",
      """""",
      this.prefix + """login"""
    )
  )

  // @LINE:12
  private[this] lazy val controllers_UserController_doRegister5_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("register")))
  )
  private[this] lazy val controllers_UserController_doRegister5_invoker = createInvoker(
    UserController_0.doRegister(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "doRegister",
      Nil,
      "POST",
      """""",
      this.prefix + """register"""
    )
  )

  // @LINE:18
  private[this] lazy val controllers_Assets_versioned6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned6_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String], fakeValue[Asset]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      """ Map static resources from the /public folder to the /assets URL path""",
      this.prefix + """assets/""" + "$" + """file<.+>"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:5
    case controllers_Application_index0_route(params) =>
      call { 
        controllers_Application_index0_invoker.call(Application_2.index())
      }
  
    // @LINE:7
    case controllers_UserController_characters1_route(params) =>
      call { 
        controllers_UserController_characters1_invoker.call(UserController_0.characters())
      }
  
    // @LINE:9
    case controllers_UserController_loginForm2_route(params) =>
      call { 
        controllers_UserController_loginForm2_invoker.call(UserController_0.loginForm())
      }
  
    // @LINE:10
    case controllers_UserController_registerForm3_route(params) =>
      call { 
        controllers_UserController_registerForm3_invoker.call(UserController_0.registerForm())
      }
  
    // @LINE:11
    case controllers_UserController_doLogin4_route(params) =>
      call { 
        controllers_UserController_doLogin4_invoker.call(UserController_0.doLogin())
      }
  
    // @LINE:12
    case controllers_UserController_doRegister5_route(params) =>
      call { 
        controllers_UserController_doRegister5_invoker.call(UserController_0.doRegister())
      }
  
    // @LINE:18
    case controllers_Assets_versioned6_route(params) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned6_invoker.call(Assets_1.versioned(path, file))
      }
  }
}
