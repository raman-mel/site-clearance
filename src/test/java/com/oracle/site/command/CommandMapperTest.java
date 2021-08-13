package com.oracle.site.command;


import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import com.oracle.site.service.SiteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CommandMapperTest {

    @Mock
    private SiteService siteService;

    private CommandMapper commandMapper;

    @BeforeEach
    public void init() {
        commandMapper = new CommandMapper(siteService);
    }

    @Test
    public void itShouldReturnAdvanceCommand1() {
        assertThat(commandMapper.apply("a 4"), instanceOf(AdvanceCommand.class));
    }

    @Test
    public void itShouldReturnAdvanceCommand2() {
        assertThat(commandMapper.apply("advance 10"), instanceOf(AdvanceCommand.class));
    }

    @Test
    public void itShouldReturnAdvanceCommand3() {
        assertThat(commandMapper.apply("ADVANCE 6"), instanceOf(AdvanceCommand.class));
    }

    @Test
    public void itShouldReturnAdvanceCommand4() {
        assertThat(commandMapper.apply("A 2"), instanceOf(AdvanceCommand.class));
    }

    @Test
    public void itShouldNotReturnAdvanceCommand1() {
        assertThat(commandMapper.apply("ad 10"), instanceOf(NoOpCommand.class));
    }

    @Test
    public void itShouldNotReturnAdvanceCommand2() {
        assertThat(commandMapper.apply("ad 10 9"), instanceOf(NoOpCommand.class));
    }

    @Test
    public void itShouldNotReturnAdvanceCommand3() {
        assertThat(commandMapper.apply("advance"), instanceOf(NoOpCommand.class));
    }

    @Test
    public void itShouldReturnLeftCommand1() {
        assertThat(commandMapper.apply("l"), instanceOf(LeftCommand.class));
    }

    @Test
    public void itShouldReturnLeftCommand2() {
        assertThat(commandMapper.apply("left"), instanceOf(LeftCommand.class));
    }

    @Test
    public void itShouldReturnLeftCommand3() {
        assertThat(commandMapper.apply("L"), instanceOf(LeftCommand.class));
    }

    @Test
    public void itShouldReturnLeftCommand4() {
        assertThat(commandMapper.apply("LEFT"), instanceOf(LeftCommand.class));
    }

    @Test
    public void itShouldReturnRightCommand1() {
        assertThat(commandMapper.apply("r"), instanceOf(RightCommand.class));
    }

    @Test
    public void itShouldReturnRightCommand2() {
        assertThat(commandMapper.apply("right"), instanceOf(RightCommand.class));
    }

    @Test
    public void itShouldReturnRightCommand3() {
        assertThat(commandMapper.apply("R"), instanceOf(RightCommand.class));
    }

    @Test
    public void itShouldReturnRightCommand4() {
        assertThat(commandMapper.apply("RIGHT"), instanceOf(RightCommand.class));
    }

    @Test
    public void itShouldReturnQuitCommand1() {
        assertThat(commandMapper.apply("q"), instanceOf(QuitCommand.class));
    }

    @Test
    public void itShouldReturnQuitCommand2() {
        assertThat(commandMapper.apply("quit"), instanceOf(QuitCommand.class));
    }

    @Test
    public void itShouldReturnQuitCommand3() {
        assertThat(commandMapper.apply("Q"), instanceOf(QuitCommand.class));
    }

    @Test
    public void itShouldReturnQuitCommand4() {
        assertThat(commandMapper.apply("QUIT"), instanceOf(QuitCommand.class));
    }

}