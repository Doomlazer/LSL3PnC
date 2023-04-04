;;; Sierra Script 1.0 - (do not remove this comment)
(script# 305)
(include sci.sh)
(use Main)
(use n021)
(use AutoDoor)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm305 0
)

(local
	[plotString 307]
)
(instance rm305 of Rm
	(properties
		picture 305
		east 250
		west 350
	)
	
	(method (init)
		(super init:)
		(addToPics
			add: atpPalmTree1
			add: atpPalmTree2
			add: atpPalmTree3
			add: atpPalmTree3a
			add: atpPalmTree4
			add: atpPalmTree5
			add: atpPalmTree6
			add: atpPalmTree7
			add: atpPalmTree8
			add: atpPalmTree9
			add: atpPalmTree10
			doit:
		)
		(if (> global87 16)
			(aSign setPri: 12 setCycle: Fwd isExtra: 1 init:)
		)
		(aDoor init:)
		(self setScript: RoomScript)
		(if (== prevRoomNum 350)
			(ego posn: 78 144 loop: 0)
		else
			(ego posn: 313 161)
		)
		(NormalEgo)
		(ego init:)
		(User canInput: 0 mapKeyToDir: 0)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (changeState newState)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0
				(if (== gCurRoomNum 4)
					(Bset 22)
					(= gCurRoomNum 0)
					(= state 1)
					(= cycles 20)
				)
			)
			(1)
			(2 (Print 305 5))
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			((Said 'look<in/cup') (Print 305 0))
			((Said 'look>')
				(cond 
					((Said '/awning') (Print 305 1))
					((Said '/palm,palm') (Print 305 2))
					((Said '[/building,area]')
						(Print 305 3)
						(if (not musicLoop) (Print 305 4 #at -1 144))
					)
				)
			)
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emSHIFT))
				)
				(if
					(and
						(> (event x?) 300)
						(< (event x?) 319)
						(> (event y?) 140)
						(< (event y?) 180)
					)
					(event claimed: 1)
					(switch theCursor
						(999
							(ego setMotion: MoveTo 324 171)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(or
						(proc0_26 atpPalmTree1 (event x?) (event y?))
						(proc0_26 atpPalmTree2 (event x?) (event y?))
						(proc0_26 atpPalmTree3 (event x?) (event y?))
						(proc0_26 atpPalmTree3a (event x?) (event y?))
						(proc0_26 atpPalmTree4 (event x?) (event y?))
						(proc0_26 atpPalmTree5 (event x?) (event y?))
						(proc0_26 atpPalmTree6 (event x?) (event y?))
						(proc0_26 atpPalmTree7 (event x?) (event y?))
						(proc0_26 atpPalmTree8 (event x?) (event y?))
						(proc0_26 atpPalmTree9 (event x?) (event y?))
						(proc0_26 atpPalmTree10 (event x?) (event y?))
					)
					(event claimed: 1)
					(switch theCursor
						(998
							(switch (Random 1 2)
								(1 (Print 305 2))
								(2
									(Print 305 3)
									(if (not musicLoop) (Print 305 4 #at -1 144))
								)
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if (proc0_26 aSign (event x?) (event y?))
					(event claimed: 1)
					(switch theCursor
						(998 (Print 305 1))
						(else  (event claimed: 0))
					)
				)
			)
		)
	)
)

(instance atpPalmTree1 of PV
	(properties
		y 128
		x 143
		view 305
		loop 1
		priority 9
	)
)

(instance atpPalmTree2 of PV
	(properties
		y 131
		x 183
		view 305
		loop 1
		priority 9
	)
)

(instance atpPalmTree3 of PV
	(properties
		y 135
		x 225
		view 305
		loop 1
		priority 9
	)
)

(instance atpPalmTree3a of PV
	(properties
		y 139
		x 266
		view 305
		loop 1
		priority 9
	)
)

(instance atpPalmTree4 of PV
	(properties
		y 144
		x 311
		view 305
		loop 1
		priority 10
	)
)

(instance atpPalmTree5 of PV
	(properties
		y 161
		x 42
		view 305
		loop 1
		cel 1
		priority 13
		signal $4000
	)
)

(instance atpPalmTree6 of PV
	(properties
		y 169
		x 90
		view 305
		loop 1
		cel 1
		priority 14
		signal $4000
	)
)

(instance atpPalmTree7 of PV
	(properties
		y 175
		x 140
		view 305
		loop 1
		cel 1
		priority 15
		signal $4000
	)
)

(instance atpPalmTree8 of PV
	(properties
		y 180
		x 190
		view 305
		loop 1
		cel 1
		priority 15
		signal $4000
	)
)

(instance atpPalmTree9 of PV
	(properties
		y 185
		x 238
		view 305
		loop 1
		cel 1
		priority 15
		signal $4000
	)
)

(instance atpPalmTree10 of PV
	(properties
		y 192
		x 287
		view 305
		loop 1
		cel 1
		priority 15
		signal $4000
	)
)

(instance aSign of Prop
	(properties
		y 60
		x 149
		view 305
		loop 2
	)
)

(instance aDoor of AutoDoor
	(properties
		y 86
		x 93
		view 305
		entranceTo 350
	)
	
	(method (init)
		(super init:)
		(self setPri: 5)
	)
)
