import {connect} from 'react-redux';
import {accountAction} from '../_actions';
import React, {Component} from 'react';
import AppBar from '../_components/appbar';
import PropTypes from 'prop-types';
import {withStyles} from '@material-ui/core/styles';
import Nav from '../_components/nav';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';
import EditIcon from '@material-ui/icons/Edit';
import IconButton from '@material-ui/core/IconButton';
import {withRouter} from 'react-router-dom';

const drawerWidth = 240;
const styles = theme => ({
    root: {
        flexGrow: 1,
    },
    appFrame: {
        zIndex: 1,
        overflow: 'hidden',
        position: 'relative',
        display: 'flex',
        width: '100%',
    },
    appBar: {
        width: `calc(100% - ${drawerWidth}px)`,
    },
    'appBar-left': {
        marginLeft: drawerWidth,
    },
    'appBar-right': {
        marginRight: drawerWidth,
    },
    drawerPaper: {
        position: 'relative',
        width: drawerWidth,
    },
    toolbar: theme.mixins.toolbar,
    content: {
        flexGrow: 1,
        backgroundColor: theme.palette.background.default,
        padding: theme.spacing(3),
    },
    paper: {
        position: 'absolute',
        width: theme.spacing(50),
        backgroundColor: theme.palette.background.paper,
        boxShadow: theme.shadows[5],
        padding: theme.spacing(4),
    },
});

class Account extends Component {
    componentDidMount() {
        const {dispatch} = this.props;
        dispatch(accountAction.getAccount());
    }

    handleChange = event => {
        this.setState({
            anchor: event.target.value,
        });
    };
    handleClick = (event, id) => {
        const {dispatch} = this.props;
        dispatch(accountAction.deleteAccountById(id))
    };

    render() {
        const {classes} = this.props;
        const {account} = this.props.account;
        return (
            <div className={classes.root}>
                <div className={classes.appFrame}>
                    <AppBar/>
                    <Nav/>
                    <main className={classes.content}>
                        <div className={classes.toolbar}/>
                        <Grid container spacing={3}>
                            <Paper className={classes.root}>
                                <Table className={classes.table}>
                                    <TableHead>
                                        <TableRow>
                                            <TableCell>Имя</TableCell>
                                            <TableCell>Фамилия</TableCell>
                                            <TableCell>Возраст</TableCell>
                                            <TableCell>Город</TableCell>
                                            <TableCell>Действия</TableCell>
                                        </TableRow>
                                    </TableHead>
                                    <TableBody>
                                        {account.map(n => {
                                            return (
                                                <TableRow key={n.id}>
                                                    <TableCell component="th" scope="row">
                                                        {n.firstName}
                                                    </TableCell>
                                                    <TableCell>{n.lastName}</TableCell>
                                                    <TableCell>{n.age}</TableCell>
                                                    <TableCell>{n.city}</TableCell>
                                                    <TableCell>
                                                        <IconButton className={classes.button} aria-label="Edit"
                                                                    component='a' href={`/view-account/${n.id}`}>
                                                            <EditIcon/>
                                                        </IconButton>
                                                    </TableCell>
                                                </TableRow>
                                            );
                                        })}
                                    </TableBody>
                                </Table>
                            </Paper>
                        </Grid>
                    </main>
                </div>
            </div>
        );
    }
}

Account.propTypes = {
    classes: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => {
    return {
        account: state.account
    };
};

const connectedAccountPage = withRouter(connect(mapStateToProps, null, null, {
    pure: false
})(withStyles(styles)(Account)));
export {connectedAccountPage as Account};