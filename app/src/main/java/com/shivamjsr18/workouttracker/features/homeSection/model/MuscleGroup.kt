package com.shivamjsr18.workouttracker.features.homeSection.model

enum class MuscleGroup {
    Back,
    Biceps,
    Chest,
    Triceps,
    Forearms,
    Legs,
    Core,
    Cardio;
    companion object{
        fun getMuscleGroupList(): List<String>{
            return entries.map{
                it.name
            }
        }
    }
}