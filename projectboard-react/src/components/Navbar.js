import React from "react";
import PropTypes from "prop-types";
import { withStyles } from "@material-ui/core/styles";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Button from "@material-ui/core/Button";
import Logo from "../images/logo_transparent.png";

const styles = {
  root: {
    flexGrow: 1
  },
  grow: {
    flex: 1
  },
  margin: {
    "margin-left": "auto"
  },
  img: {
    height: "80px"
  }
};

function Navbar(props) {
  const { classes } = props;
  return (
    <div className={classes.root}>
      <AppBar position="static">
        <Toolbar>
          <img className={classes.img} alt="" src={Logo} />
          <Button className={classes.margin} color="inherit" border={1}>
            Login
          </Button>
          <Button color="inherit">Sign up</Button>
        </Toolbar>
      </AppBar>
    </div>
  );
}

Navbar.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(Navbar);
