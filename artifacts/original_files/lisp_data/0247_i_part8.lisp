
(loop :for page :from 30 :upto 40 :do
   (let ((links (get-links page)))
     (unless links
       (loop-finish))
     (loop :for link :in links :do
        (print link)
        (setf (gethash link *items*) ""))))