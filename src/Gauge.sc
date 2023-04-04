;;; Sierra Script 1.0 - (do not remove this comment)
(script# 987)
(include sci.sh)
(use Main)
(use Intrface)
(use Save)


(local
	textI
	upI
	downI
	gaugeI
	okI
	normalI
	noI
	[str 40]
)
(class Gauge of Dialog
	(properties
		elements 0
		size 0
		text 0
		window 0
		theItem 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		time 0
		busy 0
		seconds 0
		lastSeconds 0
		description 0
		higher {up}
		lower {down}
		normal 7
		minimum 0
		maximum 15
	)
	
	(method (init param1 &tmp temp0 temp1)
		(= window SysWindow)
		(self update: param1)
		((= downI (DButton new:))
			text: lower
			moveTo: 4 4
			setSize:
		)
		(self add: downI setSize:)
		((= gaugeI (DText new:))
			text: @str
			moveTo: (+ (downI nsRight?) 4) 4
			font: 0
			setSize:
		)
		(self add: gaugeI setSize:)
		((= upI (DButton new:))
			text: higher
			moveTo: (+ (gaugeI nsRight?) 4) 4
			setSize:
		)
		(self add: upI setSize:)
		(= nsBottom (+ nsBottom 8))
		((= okI (DButton new:))
			text: {OK}
			setSize:
			moveTo: 4 nsBottom
		)
		((= normalI (DButton new:))
			text: {Normal}
			setSize:
			moveTo: (+ (okI nsRight?) 4) nsBottom
		)
		((= noI (DButton new:))
			text: {Cancel}
			setSize:
			moveTo: (+ (normalI nsRight?) 4) nsBottom
		)
		(self add: okI normalI noI setSize:)
		(= temp0 (- (- nsRight (noI nsRight?)) 4))
		((= textI (DText new:))
			text: description
			font: smallFont
			setSize: (- nsRight 8)
			moveTo: 4 4
		)
		(= temp1 (+ (textI nsBottom?) 4))
		(self add: textI)
		(upI move: 0 temp1)
		(downI move: 0 temp1)
		(gaugeI move: 0 temp1)
		(okI move: temp0 temp1)
		(normalI move: temp0 temp1)
		(noI move: temp0 temp1)
		(self setSize: center: open: 4 15)
	)
	
	(method (doit theTheNormal &tmp temp0 theNormal)
		(self init: theTheNormal)
		(= theNormal theTheNormal)
		(repeat
			(self update: theNormal)
			(gaugeI draw:)
			(= temp0 (super doit: okI))
			(cond 
				((== temp0 upI) (if (< theNormal maximum) (++ theNormal)))
				((== temp0 downI) (if (> theNormal minimum) (-- theNormal)))
				(else
					(if (== temp0 okI) (break))
					(cond 
						((== temp0 normalI) (= theNormal normal))
						((or (== temp0 0) (== temp0 noI)) (= theNormal theTheNormal) (break))
					)
				)
			)
		)
		(self dispose:)
		(return theNormal)
	)
	
	(method (update param1 &tmp temp0 temp1)
		(= temp1 (- maximum minimum))
		(= temp0 0)
		(while (< temp0 temp1)
			(StrAt @str temp0 (if (< temp0 param1) 6 else 7))
			(++ temp0)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(evKEYBOARD
				(switch (event message?)
					(KEY_NUMPAD4
						(event claimed: 1)
						(return downI)
					)
					(KEY_RIGHT
						(event claimed: 1)
						(return upI)
					)
				)
			)
			(evJOYSTICK
				(switch (event message?)
					(JOY_LEFT
						(event claimed: 1)
						(return downI)
					)
					(JOY_RIGHT
						(event claimed: 1)
						(return upI)
					)
				)
			)
		)
		(return (super handleEvent: event))
	)
)
