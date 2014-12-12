(ns webapp.main)

(def my-element (js/jQuery.parseHTML "<div></div>"))

(.ready (js/jQuery js/document) (fn [] (.append (js/jQuery "body") my-element)))



; Füge eine Überschrift hinzu.
(def my-header (js/jQuery.parseHTML "<h1></h1>"))
(.append (js/jQuery my-element) my-header)

(.text (js/jQuery my-header) "!! Hallo Clojure-Script !!")



; Link mit nem alert
(def my-link (js/jQuery.parseHTML "<a href=\"#\"></a>"))
(.append (js/jQuery my-element) my-link)
(.text (js/jQuery my-link) "Ein Link")

(def ticker (atom 0))

(defn jquery-get-position
  [selector]
  (let [p (.position (js/jQuery selector))]
    {:top p.top, :left p.left}
    ))

(defn my-link-on-click
  []
  (do
    (swap! ticker inc)
    (js/alert (str
                "Der Ticker ist auf " @ticker " "
                "Und die Position top ist " (let [p (jquery-get-position my-link)]
                                              (str
                                                "top: " (:top p) " "
                                                "left: " (:left p)
                                                ))
                ))
    false
    ))

(.click (js/jQuery my-link) my-link-on-click)
