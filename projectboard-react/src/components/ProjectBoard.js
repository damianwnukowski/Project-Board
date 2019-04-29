import React from "react";
import PropTypes from "prop-types";
import { withStyles } from "@material-ui/core/styles";
import AppBar from "@material-ui/core/AppBar";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import Grid from "@material-ui/core/Grid";
import ProjectCard from "./ProjectCard";
import AddIcon from "@material-ui/icons/Add";
import Fab from "@material-ui/core/Fab";
import { Modal, Typography } from "@material-ui/core";

const styles = theme => ({
  root: {
    flexGrow: 1
  },
  grid: {
    padding: theme.spacing.unit,
    margin: 0, //
    width: "100%" // margin and width changed due to unintended scrollbar
  },
  appBar: {
    backgroundColor: "#ffffff"
  },
  fab: {
    position: "absolute",
    bottom: theme.spacing.unit * 5,
    right: theme.spacing.unit * 5
  }
});

class ProjectBoard extends React.Component {
  state = {
    value: 0,
    modalOpened: false
  };

  handleChange = (event, value) => {
    this.setState({ value });
  };

  handleFabOnClick = () => {
    this.setState({ value: 0, modalOpened: true });
  };

  render() {
    const { classes } = this.props;
    const { value } = this.state;

    return (
      <div className={classes.root}>
        <AppBar position="static" className={classes.appBar}>
          <Tabs value={value} onChange={this.handleChange}>
            <Tab label="To do" />
            <Tab label="In progress" />
            <Tab label="Done" />
          </Tabs>
        </AppBar>
        {value === 0 && (
          <Grid container spacing={16} className={classes.grid}>
            <Grid item>
              <ProjectCard />
            </Grid>
            <Grid item>
              <ProjectCard />
            </Grid>
          </Grid>
        )}
        {value === 1 && <Grid>In progress</Grid>}
        {value === 2 && <Grid>Done</Grid>}
        <Fab
          onClick={this.handleFabOnClick}
          color="secondary"
          className={classes.fab}
        >
          <AddIcon />
        </Fab>
        <Modal open={this.state.modalOpened}>
          <Typography>Test</Typography>
        </Modal>
      </div>
    );
  }
}

ProjectBoard.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(ProjectBoard);
