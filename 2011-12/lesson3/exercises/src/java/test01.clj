(ns interop.test01)

; =============================
; === Java interoperability ===
; =============================

(import
    '(java.util Calendar GregorianCalendar)
    '(javax.swing JFrame JLabel))

; access constants
(= (. java.util.Calendar APRIL) (Calendar/APRIL))

; creation of instance of Java class
(def calendar (new GregorianCalendar 2008 Calendar/APRIL 16)) ; April 16, 2008
(def calendar (GregorianCalendar. 2008 Calendar/APRIL 16))

; getters
(. calendar add Calendar/MONTH 2)
(. calendar get Calendar/MONTH) 
(.add calendar Calendar/MONTH 2)
(.get calendar Calendar/MONTH) 


; functions chaining
(. (. calendar getTimeZone) getDisplayName) ; long way
(.. calendar getTimeZone getDisplayName) ; -> "Central Standard Time"

; doto invoke multiple functions on the given object
(doto calendar
    (.set Calendar/YEAR 1981)
    (.set Calendar/MONTH Calendar/AUGUST)
    (.set Calendar/DATE 1))
(def formatter (java.text.DateFormat/getDateInstance))
(.format formatter (.getTime calendar)) ; -> "Aug 1, 1981"

; thread invocation
(defn delayed-print [ms text]
    (Thread/sleep ms)
    (println text))

; every anonymous function implements Runnable interface
(.start (Thread. #(delayed-print 1000 ", World!")))  
(print "Hello")  


