
(defgeneric locative-args (locative)
  (:documentation "The REST of LOCATIVE if it's a list. If it's a symbol then
  it's ().")
  (:method ((locative list))
    (rest locative))
  (:method ((locative t))
    nil))