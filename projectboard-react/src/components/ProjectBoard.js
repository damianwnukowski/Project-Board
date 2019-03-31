import React from "react";
import PropTypes from "prop-types";
import { withStyles } from "@material-ui/core/styles";
import AppBar from "@material-ui/core/AppBar";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import Grid from "@material-ui/core/Grid";
import ProjectCard from "./ProjectCard";

const styles = {
  root: {
    flexGrow: 1
  },
  grid: {
    padding: 10
  }
};

class ProjectBoard extends React.Component {
  state = {
    value: 0
  };

  handleChange = (event, value) => {
    this.setState({ value });
  };

  render() {
    const { classes } = this.props;
    const { value } = this.state;

    return (
      <div className={classes.root}>
        <AppBar position="static">
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
      </div>
    );
  }
}

ProjectBoard.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(ProjectBoard);
