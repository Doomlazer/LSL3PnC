;;; Sierra Script 1.0 - (do not remove this comment)
(script# 22)
(include sci.sh)
(use Main)
(use Intrface)
(use File)
(use Game)
(use User)
(use System)

(public
	rm22 0
)

(instance rm22 of Locale
	(properties)
	
	(method (handleEvent event &tmp temp0 [temp1 3] [temp4 30] [temp34 30] [temp64 30])
		(if (or (not (Btst 14)) (event claimed?)) (return))
		(switch (event type?)
			(evKEYBOARD
				(switch (event message?)
					(KEY_ALT_c
						(Show 4)
						(Animate (cast elements?) 0)
						(while (== 0 ((= event (Event new:)) type?))
							(event dispose:)
						)
						(event dispose:)
						(Show 1)
						(return)
					)
					(KEY_ALT_d (SetDebug))
					(KEY_ALT_i (User canInput: 1))
					(KEY_ALT_m (theGame showMem:))
					(KEY_ALT_n
						(= temp34 0)
						(= temp64 0)
						(= temp0 1)
						(++ global183)
						(while
						(GetInput @temp64 40 {Press "Enter" or "ESC" when done.})
							(Format @temp4 22 0 (theGame name?) @global180)
							(Format
								@temp34
								22
								1
								curRoomNum
								version
								@global175
								@global180
								global183
								temp0
								(ego view?)
								(ego x?)
								(ego y?)
							)
							(gamefile_sh
								name: @temp4
								write: @temp34 @temp64 {\0D\n}
								close:
							)
							(= temp64 0)
							(++ temp0)
						)
					)
					(KEY_ALT_p (Show 2))
					(KEY_ALT_r
						(Print (Format @temp4 22 2 curRoomNum))
					)
					(KEY_ALT_v (Show 1))
					(KEY_ALT_x)
					(KEY_ALT_z (= quit 1))
					(JOY_DOWNRIGHT
						(= temp0 (GetNumber {Teleport to}))
						(if (Load rsSCRIPT temp0)
							(NormalEgo)
							(curRoom newRoom: temp0)
						else
							(Print 22 3)
							(SetDebug)
						)
					)
					(JOY_DOWN
						(Print
							(Format
								@temp4
								{view %d loop %d cel %d posn %d %d pri %d OnControl $%x Origin on $%x}
								(ego view?)
								(ego loop?)
								(ego cel?)
								(ego x?)
								(ego y?)
								(ego priority?)
								(ego onControl:)
								(ego onControl: 1)
							)
							#icon
							(ego view?)
							(ego loop?)
							(ego cel?)
						)
					)
					(JOY_UPLEFT (theGame showMem:))
				)
			)
			(evSAID
				(if (Said 'tp')
					(= temp0 (GetNumber {Teleport to}))
					(if (Load rsSCRIPT temp0)
						(NormalEgo)
						(curRoom newRoom: temp0)
					else
						(Print 22 3)
						(SetDebug)
					)
				)
			)
		)
		(if (not (event claimed?)) (super handleEvent: event))
	)
)
