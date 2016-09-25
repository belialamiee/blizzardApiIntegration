
// @GENERATOR:play-routes-compiler
// @SOURCE:/var/www/uni/overwatch/conf/routes
// @DATE:Mon Sep 12 17:43:39 AEST 2016


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
