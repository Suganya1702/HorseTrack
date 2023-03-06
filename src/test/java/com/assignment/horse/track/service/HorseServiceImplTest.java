package com.assignment.horse.track.service;

import com.assignment.horse.track.model.Horse;
import com.assignment.horse.track.repository.HorseRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HorseServiceImplTest {

    @InjectMocks
    HorseServiceImpl horseService;

    @Mock
    HorseRepository horseRepository;

    @Mock
    PrintMessageToUserService printMessageToUserService;
    @Test
    void updateHorseDidWin_ShouldUpdateWonOrLost(){
        List<Horse> horseList = new ArrayList<>();
        horseList.add(new Horse(1,"That darn gray cat",5,"won"));
        horseList.add(new Horse(2,"Fort Utopia",10,"lost"));
        horseList.add(new Horse(3,"Count Sheep",9,"lost"));
        horseList.add(new Horse(4,"Ms Traitour",4,"lost"));
        horseList.add(new Horse(5,"Real Princess",3,"lost"));
        horseList.add(new Horse(6,"Pa Kettle",5,"lost"));
        horseList.add(new Horse(7,"Gin Stinger",6,"lost"));
        when(horseRepository.findAll()).thenReturn(horseList);
        when(horseRepository.save(Mockito.any())).thenReturn(new Horse(2,"Fort Utopia",10,"won"));
        Assert.assertEquals("won",horseService.updateHorseDidWin(1).getDidWon());
        when(horseRepository.save(Mockito.any())).thenReturn(new Horse(1,"That darn gray cat",5,"lost"));
        Assert.assertEquals("lost",horseService.updateHorseDidWin(0).getDidWon());

    }
    @Test
    public void isValidHorseNumberEntered_WhenValidHorseNumberEntered_ReturnsTrue(){
        List<Horse> horseList = new ArrayList<>();
        horseList.add(new Horse(1,"That darn gray cat",5,"won"));
        horseList.add(new Horse(2,"Fort Utopia",10,"lost"));
        horseList.add(new Horse(3,"Count Sheep",9,"lost"));
        horseList.add(new Horse(4,"Ms Traitour",4,"lost"));
        horseList.add(new Horse(5,"Real Princess",3,"lost"));
        horseList.add(new Horse(6,"Pa Kettle",5,"lost"));
        horseList.add(new Horse(7,"Gin Stinger",6,"lost"));
        when(horseRepository.findAll()).thenReturn(horseList);
        Assert.assertTrue(horseService.isValidHorseNumberEntered(7));

    }

    @Test
    public void isValidHorseNumberEntered_WhenInValidHorseNumberEntered_ReturnsFalse(){
        List<Horse> horseList = new ArrayList<>();
        horseList.add(new Horse(1,"That darn gray cat",5,"won"));
        horseList.add(new Horse(2,"Fort Utopia",10,"lost"));
        horseList.add(new Horse(3,"Count Sheep",9,"lost"));
        horseList.add(new Horse(4,"Ms Traitour",4,"lost"));
        horseList.add(new Horse(5,"Real Princess",3,"lost"));
        horseList.add(new Horse(6,"Pa Kettle",5,"lost"));
        horseList.add(new Horse(7,"Gin Stinger",6,"lost"));
        when(horseRepository.findAll()).thenReturn(horseList);
        Assert.assertFalse(horseService.isValidHorseNumberEntered(9));

    }

    @Test
    public void didHorseWon_WhenWonHorseIndexGiven_ReturnTrue(){
        List<Horse> horseList = new ArrayList<>();
        horseList.add(new Horse(1,"That darn gray cat",5,"won"));
        horseList.add(new Horse(2,"Fort Utopia",10,"lost"));
        horseList.add(new Horse(3,"Count Sheep",9,"lost"));
        horseList.add(new Horse(4,"Ms Traitour",4,"lost"));
        horseList.add(new Horse(5,"Real Princess",3,"lost"));
        horseList.add(new Horse(6,"Pa Kettle",5,"lost"));
        horseList.add(new Horse(7,"Gin Stinger",6,"lost"));
        when(horseRepository.findAll()).thenReturn(horseList);
        Assert.assertTrue(horseService.didHorseWon(1));
    }

    @Test
    public void didHorseWon_WhenLostHorseIndexGiven_ReturnFalse(){
        List<Horse> horseList = new ArrayList<>();
        horseList.add(new Horse(1,"That darn gray cat",5,"won"));
        horseList.add(new Horse(2,"Fort Utopia",10,"lost"));
        horseList.add(new Horse(3,"Count Sheep",9,"lost"));
        horseList.add(new Horse(4,"Ms Traitour",4,"lost"));
        horseList.add(new Horse(5,"Real Princess",3,"lost"));
        horseList.add(new Horse(6,"Pa Kettle",5,"lost"));
        horseList.add(new Horse(7,"Gin Stinger",6,"lost"));
        when(horseRepository.findAll()).thenReturn(horseList);
        Assert.assertFalse(horseService.didHorseWon(2));
    }

    @Test
    public void getWinningAmount_WhenCommandsEntered_ReturnsWinningAmt(){
        List<Horse> horseList = new ArrayList<>();
        horseList.add(new Horse(1,"That darn gray cat",5,"won"));
        horseList.add(new Horse(2,"Fort Utopia",10,"lost"));
        horseList.add(new Horse(3,"Count Sheep",9,"lost"));
        horseList.add(new Horse(4,"Ms Traitour",4,"lost"));
        horseList.add(new Horse(5,"Real Princess",3,"lost"));
        horseList.add(new Horse(6,"Pa Kettle",5,"lost"));
        horseList.add(new Horse(7,"Gin Stinger",6,"lost"));
        when(horseRepository.findAll()).thenReturn(horseList);
        Assert.assertEquals(275,horseService.getWinningAmount(1,55));
    }

    @Test
    public void getHorseName_WhenHorseIndexPassed_ReturnsHorseName(){
        List<Horse> horseList = new ArrayList<>();
        horseList.add(new Horse(1,"That darn gray cat",5,"won"));
        horseList.add(new Horse(2,"Fort Utopia",10,"lost"));
        horseList.add(new Horse(3,"Count Sheep",9,"lost"));
        horseList.add(new Horse(4,"Ms Traitour",4,"lost"));
        horseList.add(new Horse(5,"Real Princess",3,"lost"));
        horseList.add(new Horse(6,"Pa Kettle",5,"lost"));
        horseList.add(new Horse(7,"Gin Stinger",6,"lost"));
        when(horseRepository.findAll()).thenReturn(horseList);
        Assert.assertEquals("That darn gray cat",horseService.getHorseName(0));
    }

}
