;;; Sierra Script 1.0 - (do not remove this comment)
(script# 997)
(include sci.sh)
(use Intrface)

(public
	ToggleSound 1
)

(local
	oldPause
)
(procedure (ToggleSound &tmp temp0)
	(= temp0 (DoSound sndGET_AUDIO_CAPABILITY))
	(DoSound sndGET_AUDIO_CAPABILITY (not temp0))
	(if temp0
		(SetMenu 1282 110 {Turn On})
	else
		(SetMenu 1282 110 {Turn Off})
	)
)

(class TheMenuBar of Class_255_0
	(properties
		state $0000
	)
	
	(method (init)
		(AddMenu { \01_} {About Template`^a:})
	)
	
	(method (handleEvent event &tmp [temp0 224])
		(super handleEvent: event)
		(switch event
			(257
				(Print {PnC Template Game} #title {About})
			)
		)
	)
)
